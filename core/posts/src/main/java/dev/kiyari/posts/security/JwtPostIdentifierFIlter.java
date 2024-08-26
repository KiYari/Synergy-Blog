package dev.kiyari.posts.security;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import ru.dev.kiyari.auth.service.provider.JwtAuthenticationProvider;
import ru.dev.kiyari.auth.util.CachedBodyHttpServletRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtPostIdentifierFIlter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final JwtAuthenticationProvider authorizationProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        CachedBodyHttpServletRequest cachedRequest = new CachedBodyHttpServletRequest(request);

        if (cachedRequest.getMethod().equalsIgnoreCase("POST") || request.getMethod().equalsIgnoreCase("PUT") || request.getMethod().equalsIgnoreCase("DELETE")) {
            var authHeader = cachedRequest.getHeader(HEADER_NAME);

            var jwt = authHeader.substring(BEARER_PREFIX.length());

            var id = authorizationProvider.extractClaim(jwt, c -> c.get("id", Long.class));
            var body = cachedRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            Map<String, String> jsonMap = new Gson().fromJson(body, new TypeToken<HashMap<String, Object>>() {
            }.getType());

            Long authorId = Long.valueOf(jsonMap.get("authorId"));

            if (authorId.equals(id)) {
                filterChain.doFilter(cachedRequest, response);
                return;
            }

            throw new IllegalArgumentException("Unauthorized access");
        }

        filterChain.doFilter(request, response);
    }
}
