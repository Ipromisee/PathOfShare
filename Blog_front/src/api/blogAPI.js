import request from "@/util/request";

const getBlogAPI = (userid) => {
    return request.get("/blog/getByUser/" + userid);
}

const addBlogAPI = (data) => {
    return request.post("/blog/add", data);
}
