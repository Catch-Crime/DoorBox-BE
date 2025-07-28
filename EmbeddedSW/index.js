const express = require('express');
const axios = require('axios');
const dotenv = require('dotenv');
const { swaggerUi, specs } = require('./swagger');

// 🔹 .env 파일 읽기 (최상단에서 수행)
dotenv.config();

const app = express();
const PORT = process.env.PORT || 3000;

app.use(express.json());

// 🔸 기본 라우트
app.get('/', (req, res) => {
  res.send('✅ Hello from Dockerized Node.js!');
});

// 🔸 카카오 로그인 라우트
app.post('/auth/kakao', async (req, res) => {
  const { id_token } = req.body;

  if (!id_token) {
    return res.status(400).json({ error: 'id_token is required' });
  }

  try {
    const kakaoResponse = await axios.get('https://kapi.kakao.com/v2/user/me', {
      headers: {
        Authorization: `Bearer ${id_token}`
      }
    });

    const { id, kakao_account } = kakaoResponse.data;
    const email = kakao_account.email;
    const nickname = kakao_account.profile.nickname;

    return res.status(200).json({
      kakao_id: id,
      email,
      nickname
    });
  } catch (err) {
    console.error('[카카오 토큰 검증 실패]', err.response?.data || err.message);
    return res.status(401).json({ error: 'Invalid id_token' });
  }
});

app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(specs));

// 🔸 서버 실행
app.listen(PORT, () => {
  console.log(`🚀 Server running on http://localhost:${PORT}`);
});

/**
 * @swagger
 * /auth/kakao:
 *   post:
 *     summary: 카카오 로그인
 *     description: id_token을 전달받아 카카오 서버로부터 사용자 정보를 가져옵니다.
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               id_token:
 *                 type: string
 *                 example: "eyJhbGciOi..."
 *     responses:
 *       200:
 *         description: 로그인 성공
 *       400:
 *         description: id_token 누락
 *       401:
 *         description: 인증 실패
 */
