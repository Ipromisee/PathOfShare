import request from "@/util/request";

export const getBlogAPI = (blogid) => {
    return request.get("/blog/showBlog?blogid=" + blogid);
}

export const getAllBlogAPI = () => {
    return request.get("/blog/getAll");
}

export const addBlogAPI = (data) => {
    return request.post("/blog/add", data);
}
