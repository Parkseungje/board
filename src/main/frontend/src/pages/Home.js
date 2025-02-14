import React from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100">
      <h1 className="text-4xl font-bold mb-8 text-gray-900">🏡 메인 페이지</h1>

      <div className="space-y-4">
        <button 
          className="w-60 bg-blue-500 hover:bg-blue-600 text-white font-semibold py-3 rounded-lg shadow-md transition"
          onClick={() => navigate("/posts")}
        >
          📋 게시글 목록 보기
        </button>

        <button 
          className="w-60 bg-green-500 hover:bg-green-600 text-white font-semibold py-3 rounded-lg shadow-md transition"
          onClick={() => navigate("/create-post")}
        >
          ✍ 게시글 작성하기
        </button>

        <button 
          className="w-60 bg-gray-800 hover:bg-gray-900 text-white font-semibold py-3 rounded-lg shadow-md transition"
          onClick={() => navigate("/login")}
        >
          🔐 로그인
        </button>
      </div>
    </div>
  );
};

export default Home;
