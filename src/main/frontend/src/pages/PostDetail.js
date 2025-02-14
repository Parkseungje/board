import React, { useEffect, useState, useCallback } from "react";
import { useParams, useNavigate } from "react-router-dom";

const PostDetail = () => {
  const { id } = useParams(); // ✅ URL에서 postId 가져오기
  const navigate = useNavigate();
  const [post, setPost] = useState(null);

  const fetchPost = useCallback(async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/posts/${id}`);
      const data = await response.json();
      setPost(data);
    } catch (error) {
      console.error("Error fetching post:", error);
    }
  }, [id]); 

  useEffect(() => {
    fetchPost();
  }, [fetchPost]);

  if (!post) {
    return <p className="text-center text-gray-500">Loading...</p>;
  }

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100 p-6">
      <div className="w-full max-w-2xl bg-white shadow-md rounded-lg p-6">
        
        {/* ✅ 홈으로 이동 버튼 추가 */}
        <button 
          className="mb-4 bg-gray-800 hover:bg-gray-900 text-white px-4 py-2 rounded-lg text-sm font-medium shadow-md transition"
          onClick={() => navigate("/")}
        >
          🏠 홈으로 이동
        </button>

        <h1 className="text-3xl font-bold text-gray-900">{post.title}</h1>
        <p className="mt-4 text-gray-700">{post.content}</p>

        <p className="mt-4 text-sm text-gray-500">
          <span role="img" aria-label="like">👍</span> {post.likeCount} Likes
        </p>

        {/* ✅ 뒤로 가기 버튼 추가 */}
        <button 
          className="mt-6 bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-lg text-sm font-medium shadow-md transition"
          onClick={() => navigate("/posts")}
        >
          ← 게시글 목록으로 돌아가기
        </button>
      </div>
    </div>
  );
};

export default PostDetail;
