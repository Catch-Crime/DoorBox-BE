# 1. Node.js 22 버전의 경량 Alpine Linux 기반 이미지 사용
FROM node:22-alpine

# 2. 컨테이너 내 작업 디렉토리 지정
WORKDIR /mnt

# 3. 의존성 설치용 파일만 복사
COPY package*.json ./

# 4. 의존성 설치 (node_modules 생성됨)
RUN npm install

# 5. 나머지 앱 소스 복사
COPY . .

# 6. 애플리케이션이 사용할 포트 (Express에서 3000번이라면)
EXPOSE 3000

# 7. 서버 실행 명령
CMD ["node", "index.js"]