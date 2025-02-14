import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const PostList = () => {
  const [posts, setPosts] = useState([]);
  const navigate = useNavigate(); // ✅ 페이지 이동을 위한 훅

  useEffect(() => {
    fetch("http://localhost:8080/api/posts") // Spring Boot API 호출
      .then((response) => response.json()) // JSON 변환
      .then((data) => setPosts(data)) // 상태 업데이트
      .catch((error) => console.error("Error fetching posts:", error));
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 flex justify-center p-6">
      <div className="w-full max-w-4xl bg-white shadow-md rounded-lg p-6">

        {/* ✅ 홈으로 이동 버튼 추가 */}
        <button 
          className="mb-4 bg-gray-800 hover:bg-gray-900 text-white px-4 py-2 rounded-lg text-sm font-medium shadow-md transition"
          onClick={() => navigate("/")}
        >
          🏠 홈으로 이동
        </button>

        <h1 className="text-3xl font-bold text-center mb-6 text-gray-800">
          📜 게시글 목록
        </h1>

        {posts.length === 0 ? (
          <p className="text-center text-gray-600">게시글이 없습니다.</p>
        ) : (
          <div className="overflow-x-auto">
            <table className="table-auto w-full border-collapse border border-gray-300">
              <thead>
                <tr className="bg-gray-200">
                  <th className="border border-gray-300 px-4 py-2 text-left">번호</th>
                  <th className="border border-gray-300 px-4 py-2 text-left">제목</th>
                  <th className="border border-gray-300 px-4 py-2 text-left">좋아요</th>
                </tr>
              </thead>
              <tbody>
                {posts.map((post, index) => (
                  <tr 
                    key={post.postId} 
                    className="hover:bg-gray-100 transition cursor-pointer"
                  >
                    <td className="border border-gray-300 px-4 py-2">{index + 1}</td>
                    
                    {/* ✅ 제목을 클릭하면 상세 페이지(`/post/:id`)로 이동 */}
                    <td 
                      className="border border-gray-300 px-4 py-2 font-semibold text-blue-600 hover:underline"
                      onClick={() => navigate(`/post/${post.postId}`)}
                    >
                      {post.title}
                    </td>

                    <td className="border border-gray-300 px-4 py-2 text-center">
                      <span role="img" aria-label="like">👍</span> {post.likeCount}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default PostList;
