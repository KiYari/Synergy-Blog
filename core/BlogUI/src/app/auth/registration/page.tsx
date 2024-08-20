'use client'
import BasicComponent from "@/component/basicComponent/BasicComponent";
import Registration from "@/component/auth/registration/Registration";
import styles from './page.module.css'

const RegistrationPage = () => {
    return(
        <BasicComponent className={styles.main}>
            <Registration/>
        </BasicComponent>
    )
}

export default RegistrationPage;