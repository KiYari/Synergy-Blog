import UsernamePasswordToken from "@/util/classes/UsernamePasswordToken";

const BASE_HOST = 'http://localhost:3000'
const BASE_PATHNAME = '/api/mock/auth'

export const authenticate = async (usernamePasswordToken:UsernamePasswordToken) => {
    const data = {
        username: usernamePasswordToken.username,
        password: usernamePasswordToken.password
    }

    console.log(usernamePasswordToken);

    return fetch(BASE_HOST + BASE_PATHNAME + '/login', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(data)})
        .then((res) => res.json())
        .catch((e) => {
            console.log(e)
        });
}

export const register = async (usernamePasswordToken:UsernamePasswordToken) => {
    const data = {
        username: usernamePasswordToken.username,
        password: usernamePasswordToken.password
    }

    console.log(usernamePasswordToken);

    return fetch(BASE_HOST + BASE_PATHNAME + '/registration', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(data)})
        .then((res) => res.json())
        .catch((e) => {
            console.log(e)
        });
}