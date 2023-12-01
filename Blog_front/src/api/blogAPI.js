import request from "@/util/request";

export const getBlogAPI = (blogid) => {
    return request.get("/blog/showBlog?blogid=" + blogid);
}

export const getAllBlogAPI = () => {
    return request.get("/blog/getAll");
}

export const addBlogAPI = (data) => {
    const params = new URLSearchParams();
    for (var key in data) {
        params.append(key, data[key]);
    }
    return request.post("/blog/postBlog", params);
}
