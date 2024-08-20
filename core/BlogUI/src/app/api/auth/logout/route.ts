import {NextRequest, NextResponse} from 'next/server'

export async function POST(request: NextRequest, response: NextResponse) {

    try {
        response.cookies.delete('jwtUser')
    } catch (e) {
        return NextResponse.json({
            statusCode: 400,
            message: 'You are not logged in!',
        });
    }

}