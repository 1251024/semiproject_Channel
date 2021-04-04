const canvas = document.getElementById("jsCanvas");
const ctx = canvas.getContext("2d");
const colors = document.getElementsByClassName("jsColor");
const range = document.getElementById("jsRange");
const mode = document.getElementById("jsMode");
const saveBtn = document.getElementById("jsSave");

const INITIAL_COLOR = "#2c2c2c";
const CANVAS_SIZE = 700;

canvas.width = document.getElementsByClassName("canvas")[0].offsetWidth;
canvas.height = document.getElementsByClassName("canvas")[0].offsetHeight;

// 투명배경말고 하얀 배경
ctx.fillStyle = "white";
ctx.fillRect(0, 0, canvas.width, canvas.height);
ctx.strokeStyle = INITIAL_COLOR;
ctx.fillStyle = INITIAL_COLOR;
ctx.lineWidth = 2.5;
//ctx.fillStyle = "green";
//ctx.fillRect(50, 20, 100, 49);


let painting = false;
let filling = false;

function stopPainting(){
	painting = false;
}

function startPainting(){
	painting = true;
	// 오류잡기
	if (filling){
	ctx.fillStyle
	ctx.fillRect(0, 0, canvas.width, canvas.height);
}

}

function onMouseMove(event){
	const x = event.offsetX;
	const y = event.offsetY;
	if(!painting){
		//console.log("creating path in", x, y);
		ctx.beginPath();
		ctx.moveTo(x,y);
	} else {
		//console.log("creating line in", x, y);
		ctx.lineTo(x,y);
		ctx.stroke();
		//ctx.closePath();
	}
}

/*
function onMouseDown(event){
	painting = true;
}
*/

function handleColorClick(event){
	//console.log(event.target.style);
	const color = event.target.style.backgroundColor;
	//console.log(color);
	ctx.strokeStyle = color;
	ctx.fillStyle = color;
}

function handleRangeChange(event){
	//console.log(event.target.value);
	const size = event.target.value;
	ctx.lineWidth = size;
}

function handleModeClick(){
	if(filling == true){
		filling = false;
		mode.innerText = "그리기"
	} else { 
		filling = true;
		mode.innerText = "채우기"
		//ctx.fillStyle = ctx.strokeStyle; 이거 대신에 핸들컬러클릭에 ctx.fillStyle = color; 넣어줌 
	}
}

function handleCanvasClick(){
	if(filling){
		ctx.fillRect(0, 0, canvas.width, canvas.height);
	}
}

function handleCM(event){
	// 우클릭 저장 방지 
	event.preventDefault();
}

function handleSaveClick(){
	const image = canvas.toDataURL();
	const link = document.createElement("a");
	link.href = image;
	link.download = "channel_image";
	link.click();
	
}

if(canvas){
	canvas.addEventListener("mousemove", onMouseMove);
	canvas.addEventListener("mousedown", startPainting);
	canvas.addEventListener("mouseup", stopPainting);
	canvas.addEventListener("mouseleave", stopPainting);
	canvas.addEventListener("click", handleCanvasClick);
	canvas.addEventListener("contextmenu", handleCM);
}


Array.from(colors).forEach(color => 
color.addEventListener("click", handleColorClick)
);


if(range){
	range.addEventListener("input", handleRangeChange);
}

if(mode){
	mode.addEventListener("click", handleModeClick);
}

if(saveBtn){
	saveBtn.addEventListener("click", handleSaveClick);
}


