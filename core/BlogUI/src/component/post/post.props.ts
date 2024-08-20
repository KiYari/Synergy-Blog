import BasicProps from "@/component/basicComponent/basicProps";
import Profile from "@/util/classes/Profile";


export default interface PostProps extends BasicProps {
    id: number
    title: string
    content: string
    profile: Profile
    imageUrl?: string
}