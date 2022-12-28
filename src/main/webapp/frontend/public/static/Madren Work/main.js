alert('正式模块尚未上线，这是去年练手写的屎山，麻烦将就着看看')
function refetch(params) {
    fetch('https://v1.hitokoto.cn')
    .then(response => response.json())//解码获取的api封装值
    .then(data => {
      const hitokoto = document.getElementById('yiyan')
    //   yiyan.href = 'https://hitokoto.cn/?uuid=' + data.uuid
       yiyan.innerText = '「'+data.hitokoto+'」'
       if(data.from!=null||data.from_who!=null)
       {
           yiyan.innerText+= '——'
           if(data.from_who!=null)
           {
                yiyan.innerText+=data.from_who
           }
           if(data.from!=null&&data.from_who!=null)
           {
           yiyan.innerText+='|'+data.from
           }
           else if(data.from!=null)
           {
           yiyan.innerText+=data.from
           }
        }
    })
    // yiyan.innerText = data.hitokoto+"           "+data.from!="null"||data.from_who!="null"?(data.from=="null"?'':data.from+data.from_who=="null"?'':"   "+data.from_who=="null"?'':data.from):''
    .catch(console.error)
}
fetch('https://v1.hitokoto.cn/?c=d&c=h&c=i')
    .then(response => response.json())
    .then(data => {
       yiyan.innerText = '「'+data.hitokoto+'」'
       if(data.from!=null||data.from_who!=null)
       {
           yiyan.innerText+= '——'
           if(data.from_who!=null)
           {
                yiyan.innerText+=data.from_who
           }
           if(data.from!=null&&data.from_who!=null)
           {
           yiyan.innerText+='|'+data.from
           }
           else if(data.from!=null)
           {
           yiyan.innerText+=data.from
           }
        }
    })
    // yiyan.innerText = data.hitokoto+"           "+data.from!="null"||data.from_who!="null"?(data.from=="null"?'':data.from+data.from_who=="null"?'':"   "+data.from_who=="null"?'':data.from):''
    .catch(console.error)

function dealTime(num) {
    return num>=10? num+'':'0'+num
}
function getTime() {
    let now=new Date()
    let h=now.getHours(),m=now.getMinutes(),s=now.getSeconds()
    let text=h>12?'下午':'上午'

    h=dealTime(h)
    m=dealTime(m)
    s=dealTime(s)

    let result=text+'   '+h+':'+m+':'+s
    document.getElementById('getTime').innerText=result
    setTimeout(getTime,1000)
}
window.addEventListener('load',getTime)


var webtitle='https://www.baidu.com/s?wd=';

function search(){
    if(document.getElementById('searchtxt').value!=''){
    var target=document.getElementById('searchtxt').value;
    window.open(webtitle+target);
        rtn();
}
}
function bilibili(){
    bd.removeAttribute('style')
    rn.removeAttribute('style')
    bl.style.background='linear-gradient(to right top,rgb(109,161,230),rgb(221,178,187))';
    webtitle='https://search.bilibili.com/all?keyword=';
}

function opb(){
    window.open('https://www.bilibili.com/')
    baidu()
}

function baidu(){
    bl.removeAttribute('style')
    rn.removeAttribute('style')
    bd.style.background='linear-gradient(to right top,rgb(109,161,230),rgb(221,178,187))';
    webtitle='https://www.baidu.com/s?wd=';
}

function runoob() {
    bl.removeAttribute('style')
    bd.removeAttribute('style')
    rn.style.background='linear-gradient(to right top,rgb(109,161,230),rgb(221,178,187))';
    webtitle='https://www.runoob.com/?s=';
}
function del() {
    document.getElementById('searchtxt').value='';
}
function show(){
    alert(webtitle);
}
var bool=1;
var moren=1;
function stay() {
    if(bool==1)
    {
        searchtxt.style.width='300px'
        searchtxt.style.height='30px'
        src.style.height='40px'
        btnsearch.style.height='30px'
        btnsearch.style.width='30px'
        bool=0
    }
}
function rtn() {
    searchtxt.removeAttribute('style')
    src.removeAttribute('style')
    btnsearch.removeAttribute('style')
    bool=1
    // searchtxt.style.width='0px'
    // searchtxt.style.height='10px'
    // src.style.height='20px'
    // btnsearch.style.height='10px'
    // btnsearch.style.width='10px'
}
function touge(){
    window.open('https://www.educoder.net');
}
function github(){
    window.open('https://github.com/Looouiis/MyWeb');
}












