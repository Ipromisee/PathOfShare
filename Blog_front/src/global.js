import {ref} from "vue";

export const currentUser = ref({
    id: -1,
    userName: "",
    type: ""
})

export const currentBlog = ref({
    id: -1,
    title: "",
    content: ""
})

export const setCurrentBlog = (id, title, content) => {
    currentBlog.value.id = id;
    currentBlog.value.title = title;
    currentBlog.value.content = content;
}