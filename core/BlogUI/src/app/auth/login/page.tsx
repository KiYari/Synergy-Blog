'use client'
import BasicComponent from "@/component/basicComponent/BasicComponent";
import Login from "@/component/auth/login/Login";
import styles from './page.module.css'


const LoginPage = () => {
    return(
        <BasicComponent className={styles.main}>
            <Login/>
        </BasicComponent>
    )
}

export default LoginPage;