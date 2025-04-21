const startBtn = document.getElementById('start-btn');
const messageArea = document.getElementById('message-area');
const messageInput = document.getElementById('message-input');
const sendButton = document.getElementById('send-button');
let websocket;
let sessionId;

// 백엔드 API 호출하여 외부 웹소켓 연결 요청 및 세션 ID 받기
startBtn.addEventListener('click', () => {
    console.log('request')
    fetch('http://localhost:8080/api/connect-external', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // body: JSON.stringify({ externalWebSocketUrl })
    })
        .then(response => response.text())
        .then(data => {
            sessionId = data;
            console.log('Received Session ID:', sessionId);
            connectWebSocket();
        })
        .catch(error => {
            console.error('Error connecting to external WebSocket:', error);
        });
})

function connectWebSocket() {
    if (sessionId) {
        const wsUrl = `ws://localhost:8080/ws-relay?externalId=${sessionId}`; // 백엔드 웹소켓 엔드포인트
        websocket = new WebSocket(wsUrl);

        websocket.onopen = () => {
            console.log('Connected to WebSocket');
            appendMessage('system', '웹소켓에 연결되었습니다.');
        };

        websocket.onmessage = (event) => {
            const message = event.data;
            console.log('Received message:', message);
            appendMessage('received', message);
        };

        websocket.onclose = () => {
            console.log('Disconnected from WebSocket');
            appendMessage('system', '웹소켓 연결이 끊어졌습니다.');
            websocket = null;
        };

        websocket.onerror = (error) => {
            console.error('WebSocket error:', error);
            appendMessage('system', '웹소켓 오류가 발생했습니다.');
        };
    } else {
        console.warn('Session ID가 없습니다. 웹소켓 연결을 시도할 수 없습니다.');
    }
}

sendButton.addEventListener('click', () => {
    const message = messageInput.value.trim();
    if (websocket && websocket.readyState === WebSocket.OPEN && message) {
        websocket.send(message);
        appendMessage('sent', message);
        messageInput.value = '';
    } else if (!websocket || websocket.readyState !== WebSocket.OPEN) {
        appendMessage('system', '웹소켓 연결이 되어있지 않습니다.');
    } else {
        alert('메시지를 입력하세요.');
    }
});

function appendMessage(type, message) {
    const messageDiv = document.createElement('div');
    messageDiv.classList.add('message', type);
    messageDiv.textContent = message;
    messageArea.appendChild(messageDiv);
    messageArea.scrollTop = messageArea.scrollHeight; // 스크롤 하단으로 이동
}