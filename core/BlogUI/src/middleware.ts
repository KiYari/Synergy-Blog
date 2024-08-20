import { NextResponse, NextRequest } from 'next/server'
import {revalidatePath} from "next/cache";

export function middleware(request: NextRequest, response: NextResponse) {
    const jwt = request.cookies.get('userJwt');
    const authPaths = request.url.includes('/auth')
    const ignoredPaths = request.nextUrl.pathname.startsWith("/_next") || request.nextUrl.pathname.startsWith('/api')

    const isAuthenticated = jwt !== null && jwt !== undefined;

    if (response.status === 401) {
        request.cookies.delete('userJwt')
    }

    if (isAuthenticated && authPaths) {
        return NextResponse.redirect(new URL('/', request.url))
    }

    if (authPaths || ignoredPaths || isAuthenticated) {
        return NextResponse.next();
    }

    return NextResponse.redirect(new URL('/auth/login', request.url))
}

export const config = {
    matcher: '/:path*',
}