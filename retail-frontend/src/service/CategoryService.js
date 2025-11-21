import axios from "axios";

export const addCategory = async (category)=>{
    return await axios.post('http://localhost:8848/api/v1.0/category', category)
}


export const deleteCategory = async (categoryId)=>{
    return await axios.delete(`http://localhost:8848/api/v1.0/category/${categoryId}`);
}

export const fetchCategories = async ()=>{
    return await axios.get('http://localhost:8848/api/v1.0/category');
}
