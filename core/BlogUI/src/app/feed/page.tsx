'use client'
import BasicComponent from "@/component/basicComponent/BasicComponent";
import styles from './page.module.css'
import Feed from "@/component/feed/Feed";

const FeedPage = () => {
    return(
        <BasicComponent className={styles.main}>
            <Feed/>
        </BasicComponent>
    )
}

export default FeedPage;