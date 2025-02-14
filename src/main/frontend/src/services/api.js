import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api"; // Spring Boot 서버

export const getPosts = async () => {
  const response = await axios.get(`${API_BASE_URL}/posts`);
  return response.data;
};

export const getPost = async (postId) => {
  const response = await axios.get(`${API_BASE_URL}/posts/${postId}`);
  return response.data;
};

export const createPost = async (post) => {
  const response = await axios.post(`${API_BASE_URL}/posts`, post);
  return response.data;
};

export const likePost = async (postId) => {
  await axios.post(`${API_BASE_URL}/posts/${postId}/like`);
};
