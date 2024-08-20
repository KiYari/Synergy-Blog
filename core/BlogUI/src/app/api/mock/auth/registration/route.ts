import {NextRequest, NextResponse} from 'next/server'

export async function POST(request: NextRequest) {
    const data = {token: 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c'}
    let username;
    let password;
    try {
        const json = await request.json();
        username = json.username;
        password = json.password;
    } catch (e) {
        return NextResponse.json({
            statusCode: 500,
            message: 'Internal Server Error',
        });
    }

    if (username === 'newuser' && password === 'newuser') {
        return NextResponse.json(data)
    }

    if (username === 'newuser2' && password === 'newuser2') {
        return NextResponse.json(data)
    }

    return NextResponse.json({statusCode: 400,
        message: 'username already exists!'})

}