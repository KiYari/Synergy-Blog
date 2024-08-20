import {FC} from "react";
import PostProps from "@/component/post/post.props";
import {Avatar, Card } from "antd";
import {EditOutlined, SettingOutlined, EllipsisOutlined} from "@ant-design/icons";

const { Meta } = Card;

const Post:FC<PostProps> = ({title, content, profile, imageUrl}) => {
    return(
        <Card
            style={{ minWidth: 200 }}
            cover={
                <img
                    alt=""
                    src={imageUrl}
                />
            }
        >
            <Meta
                avatar={<Avatar src={profile.avatarUrl} />}
                title={title}
                description={content}
            />
        </Card>
    )
}

export default Post
