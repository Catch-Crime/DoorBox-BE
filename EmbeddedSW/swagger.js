const swaggerUi = require('swagger-ui-express');
const swaggerJSDoc = require('swagger-jsdoc');

const options = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'Kakao Login API',
      version: '1.0.0',
      description: '소셜 로그인 백엔드 API 문서',
    },
    servers: [
      {
        url: 'http://localhost:3000',
        description: 'Local server',
      },
    ],
  },
  apis: ['./index.js'], // 주석으로 API 설명을 달 파일
};

const specs = swaggerJSDoc(options);

module.exports = { swaggerUi, specs };
