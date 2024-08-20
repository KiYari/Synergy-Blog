import React, {FC, useEffect, useState} from 'react';
import { Avatar, Divider, List, Skeleton } from 'antd';
import InfiniteScroll from 'react-infinite-scroll-component';
import FeedProps from "@/component/feed/feed.props";
import styles from './feed.module.css'
import Profile from "@/util/classes/Profile";
import Post from "@/component/post/Post";

interface DataType {
    id: number
    title: string
    content: string
    profile: Profile
}

const Feed:FC<FeedProps> = () => {
    const [loading, setLoading] = useState(false);
    const [posts, setPosts] = useState([]);
    const [offset, setOffset] = useState(0);

    const loadMoreData = () => {
        if (loading || posts.length >= 500) {
            return;
        }
        setLoading(true);
        fetch('http://localhost:3000/api/mock/post?amount=10&offset=' + offset)
            .then((res) => res.json())
            .then((body) => {
                setPosts(posts.concat(body));
                setOffset(offset+1);
                setLoading(false);
            })
            .catch(() => {
                setLoading(false);
            });
    };

    useEffect(() => {
        if (posts.length === 0) {
            loadMoreData();
        }
    }, []);

    return (
        <div
            id="scrollableDiv"
            className={styles.main}
        >
            <InfiniteScroll
                dataLength={posts.length}
                next={loadMoreData}
                hasMore={posts.length < 500}
                loader={<Skeleton paragraph={{ rows: 1 }} active />}
                endMessage={<Divider plain>It is all, nothing more 🤐</Divider>}
                scrollableTarget="scrollableDiv"
            >
                <List
                    dataSource={posts}
                    renderItem={(item:DataType) => (
                        <List.Item key={item.id}>
                            <Post id={item.id} title={item.title} content={item.content} profile={item.profile}/>
                        </List.Item>
                    )}
                />
            </InfiniteScroll>
        </div>
    );
};

export default Feed;