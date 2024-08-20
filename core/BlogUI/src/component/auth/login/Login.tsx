import React, {FC, useEffect, useState} from 'react';
import {FormProps, Typography} from 'antd';
import {useRouter} from "next/navigation";
import { Button, Checkbox, Form, Input } from 'antd';
import LoginProps from './login.props';
import styles from './login.module.css'
import Link from "next/link";
import * as userService from '@/util/service/UserService'
import UsernamePasswordToken from "@/util/classes/UsernamePasswordToken";
import {useCookies} from "react-cookie";

type FieldType = {
    username?: string;
    password?: string;
};



const Login:FC<LoginProps> = () => {
    const [cookies, setCookie, removeCookie] = useCookies(['userJwt']);
    const [error, setError] = useState('');
    const router = useRouter()

    const onFinish: FormProps<FieldType>['onFinish'] = (values) => {
        const logpass: UsernamePasswordToken = {username: values.username, password: values.password};
        const jwt = userService.authenticate(logpass)
            .then((res) => {
                const token = res.token
                if (token !== undefined && token !== null) {
                    setCookie('userJwt', token.slice(7), { path: '/' });
                    return token.slice(7)
                } else {
                    onFinishFailed?.(res.message)
                }
            })
            .then(() => console.log("ended"))
            .then(() =>{
                    console.log("refresh")
                    router.refresh()
            })

    };

    const onFinishFailed: FormProps<FieldType>['onFinishFailed'] = (errorInfo) => {
        console.log('Failed:', errorInfo);
        setError("Failed: " + errorInfo)
    };

    return (<Form
        name="basic"
        labelCol={{span: 8}}
        wrapperCol={{span: 16}}
        style={{width: 600}}
        initialValues={{remember: true}}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
        className={styles.main}
    >
        <Form.Item<FieldType>
            label="Username"
            name="username"
            rules={[{required: true, message: 'Please input your username!'}]}
        >
            <Input/>
        </Form.Item>

        <Form.Item<FieldType>
            label="Password"
            name="password"
            rules={[{required: true, message: 'Please input your password!'}]}
        >
            <Input.Password/>
        </Form.Item>

        <Form.Item wrapperCol={{offset: 8, span: 16}}>
            <Button type="primary" htmlType="submit">
                Submit
            </Button>
        </Form.Item>

        <Typography>
            {error}
        </Typography>

        <Link href='/auth/registration' className={styles.link}>No account? Create one</Link>
    </Form>)
};

export default Login;