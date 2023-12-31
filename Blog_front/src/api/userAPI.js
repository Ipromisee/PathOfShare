import request from "@/util/request";

export const loginAPI = (data) => {
    const params = new URLSearchParams();
    for (var key in data) {
        params.append(key, data[key]);
    }
    return request.get("/user/login", {params: params})
}

export const getUserInfoAPI = (userid) => {
    return request.get("/user/getUserInfo?userId=" + userid);
}