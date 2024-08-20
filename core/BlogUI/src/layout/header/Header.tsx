import {FC, useEffect} from "react";
import HeaderProps from "@/layout/header/header.props";
import styles from "./header.module.css";
import BasicComponent from "@/component/basicComponent/BasicComponent";
import Link from "next/link";
import {Button, Typography} from "antd";
import {MenuOutlined} from "@ant-design/icons";
import {useCookies} from "react-cookie";
import {useRouter} from "next/navigation";

const { Title } = Typography;

const Header:FC<HeaderProps> = ({className, style, onSiderSwitchClick, ...props}) => {
    const [cookies, setCookie, removeCookie] = useCookies(['userJwt']);
    const router = useRouter()

    const logout = () => {
        try {
            console.log("Logged out!")
            removeCookie('userJwt', { path: '/' })
            router.refresh()
        } catch (e) {
            console.log('not logged in')
        }
    }

    return(
        <BasicComponent className={`${styles.main} ${className}`} style={style} {...props}>
            <Button size='large' className={styles.siderClosable} onClick={onSiderSwitchClick} icon={<MenuOutlined className={styles.siderClosableIcon}/>}>
            </Button>

            <Link href={'/'} className={styles.homeHolder}>
                <Title level={1}>
                    Blog
                </Title>
            </Link>

            <Button type='text' onClick={logout} className={styles.logoutHolder}>
                <Title level={5}>
                    logout
                </Title>
            </Button>

            <Link href={'/profile'} className={styles.profileHolder}>
                <Title level={5}>
                    profile
                </Title>
            </Link>
        </BasicComponent>
    )
}


export default Header