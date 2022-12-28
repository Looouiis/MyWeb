// 日历部分js
(function(){//自治型的方法，形成独立的作用域，即使引入的其他文件里有同名函数也不会有影响
    var calendar={//创建面向过程的对象变量
        curDate:new Date(),  //创建当前日期属性

        init(){
            this.renderSelect(this.curDate);     //渲染下拉列表
            this.getData(this.curDate);
        },
        renderSelect(d){
            var yearList=document.querySelector('.c_yearSelect .c_selectBox ul');
            var yueList=document.querySelector('.c_yueSelect .c_selectBox ul');
            var holidayList=document.querySelector('.c_holidaySelect .c_selectBox ul');
            var yearSelected=document.querySelector('.c_yearSelect .c_selected span');
            var yueSelected=document.querySelector('.c_yueSelect .c_selected span');
            // var holidaySelected=document.querySelector('.c_holidaySelect .c_selected span');

            // 生成年份
            var li='';
            yearList.innerHTML='';
            for(var i=1900;i<=2050;i++)
            {
                li+=`<li ${i==d.getFullYear()?'class="c_active"':''}>${i}年</li>`;
            }
            yearList.innerHTML = li;
            yearSelected.innerHTML=d.getFullYear()+'年';

            // 生成月份
            var li='';
            yueList.innerHTML='';
            for(var i=1;i<=12;i++)
            {
                li+=`<li ${i==(d.getMonth()+1)?'class="c_active"':''}>${i}月</li>`;
            }
            yueList.innerHTML=li;
            yueSelected.innerHTML=d.getMonth()+1+'月';

            this.selectBindEvent();
        },
        closeSelect(){//关
            var selects =[... document.querySelectorAll('.c_select')];//类数组，不可以使用find.扩展运算符“...”可以将其转成真正的数组
            var open=selects.find(select=>select.classList.contains('c_active'));//从一堆东西中找出满足条件的那个，本行中找有active的元素
            open && open.classList.remove('c_active') //"&&":利用其左为真才执行右边进行判断的性质
        },
        selectBindEvent(){//为日期下拉框添加事件
            var selects=document.querySelectorAll('.c_select') //选中所有的下拉框

            selects.forEach((select, index)=>{//用foreach遍历下拉框，select代表下拉框，index代表每个下拉框的索引值
                var cl=select.classList;//元素身上的所有class
                var selected=select.querySelector('span');//点击的那个下拉框里的选中内容

                select.onclick =ev =>{//事件代理，减小性能占用
                    if(cl.contains('c_active')){//自己已展开，则取消展开
                        cl.remove('c_active');
                    }else {//点击的是别人
                        //关掉另一个
                        this.closeSelect();
                        //展开自己的框
                        cl.add('c_active');
                        this.scrollBar(); //在元素显示的时候显示滚动条
                    }

                    //
                    if(ev.target.tagName!='LI'){
                        return;//如果点击的不是列表，则不做操作
                    }
                    var lis =[... select.querySelectorAll('ul li')];//类数组，不可以使用find.扩展运算符“...”可以将其转成真正的数组
                    lis.find(li=>li.classList.contains('c_active')).classList.remove('c_active');
                    ev.target.classList.add('c_active');

                    // 根据索引值区分点击的下拉框是哪个
                    switch(index){
                        case 0://点击的是年
                            this.curDate.setFullYear(parseInt(ev.target.innerHTML));// paserInt：将内容转为数字，即实现去除汉字'年'
                            selected.innerHTML=ev.target.innerHTML;
                            break;
                        case 1:
                            this.curDate.setMonth(parseInt(ev.target.innerHTML)-1);
                            selected.innerHTML=ev.target.innerHTML;
                            break;
                    }
                    this.getData(this.curDate);
                };
            });
            this.monthChange();
            this.backToday();
        },
        scrollBar(){//滚动条
            var scrollWrap=document.querySelector('.c_yearSelect .c_selectBox');
            var content=document.querySelector('.c_yearSelect .c_selectBox ul');
            var barWrap=document.querySelector('.c_yearSelect .c_selectBox .c_scroll');
            var bar=document.querySelector('.c_yearSelect .c_selectBox span');

            // 初始化
            bar.style.transform=content.style.transform='translateY(0)';
            
            // 设置滑块高度
            var multiple=(content.offsetHeight+18)/scrollWrap.offsetHeight; //内容是其父级的几倍
            multiple=multiple>20?20:multiple;
            bar.style.height=scrollWrap.offsetHeight/multiple+'px';
            
            // 滑块的拖拽
            var scrollTop=0; // 滚动条活动距离
            var maxHeight=scrollWrap.offsetHeight-bar.offsetHeight; //滚动条最大滑动距离

            bar.onmousedown=function(ev){
                var startY=ev.clientY; //按下时鼠标的坐标
                var startT=parseInt(this.style.transform.split('(')[1]); //按下时据顶部的距离；split为取''内的符号后共（[]内）个元素

                //设置拖拽样式
                document.onmousemove=ev=>{
                    scrollTop=ev.clientY-startY+startT;
                    // console.log(scrollTop);
                    bar.style.transition=content.style.transition=null;
                    scroll();// 实现走动
                };
                document.onmouseup=()=>document.onmousemove=null;
            };

            barWrap.onclick=ev=>ev.stopPropagation(); //在滑块父级区间内摁下鼠标要阻止事件冒泡
            

            function scroll(){
                scrollTop=scrollTop<0?0:scrollTop;
                scrollTop=scrollTop>maxHeight?maxHeight:scrollTop;
                var mh=barWrap.offsetHeight-bar.offsetHeight-9;
                scrollTop=scrollTop>mh?mh:scrollTop;

                var scaleY=scrollTop/mh; //滚动条走的比例

                bar.style.transform='translateY('+scrollTop+'px)';
                var ct=scaleY*(scrollWrap.offsetHeight-content.offsetHeight-18);
                console.log(ct);
                content.style.transform='translateY('+ct+'px)';
            }
            
            //设置滚轮滚动
            scrollWrap.onwheel=ev=>{
                ev.deltaY>0?scrollTop+=10:scrollTop-=10; //>0往下滚，<0往下滚
                bar.style.transition=content.style.transition='.2s';
                scroll();

                ev.preventDefault();
            }
        },
        getData(d){
            $.ajax({
                url: `https://www.rili.com.cn/rili/json/pc_wnl/${d.getFullYear()}/${d.getMonth()+1}.js`,
                dataType: 'jsonp',
            });
            window.jsonrun_PcWnl=res=>{
                // console.log(res);
                this.renderDate(d,res.data);
            };
        },
        getEndDay:(year,month)=>new Date(year,month,0).getDate(),
        getFirstWeek:(year,month)=>new Date(year,month-1,1).getDay(),

        // delTag:str=>str.replace(/<\/?.+?\/?>/g,''),
        delTag:str=>str.replace(/<.+?>/g,''),
        delRi:str=>str.replace(/.+?日/g,''),
        delMore:str=>str.replace(/..../g,''),

        renderDate(d,data){
            var tbody=document.querySelector('.c_date tbody');

            var lastEndDay=this.getEndDay(d.getFullYear(),d.getMonth());
            var curEndDay=this.getEndDay(d.getFullYear(),d.getMonth()+1);
            var week=this.getFirstWeek(d.getFullYear(),d.getMonth()+1);
            var lastDateNum=week-1;
            var calendar=document.querySelector('#calendar');
            calendar.classList.remove('c_active');
            var cn=-1;//记录42次循环总共走的步数

            tbody.innerHTML='';
            week=week==0?7:week;

            for(var i=0;i<6;i++)
            {
                var tr=document.createElement('tr');
                var td='';
                for(var j=0;j<7;j++)
                {
                    cn++;
                    
                    var festival=data[cn].jie?this.delTag(data[cn].jie):data[cn].r2;
                    festival=this.delRi(festival)?festival:data[cn].r2; //我真是个大聪明！！！！！！！！！！！！
                    festival=this.delMore(festival)?festival:data[cn].r2; //我真是个大聪明！！！！！！！！！！！！
                    var weekday=data[cn].jia==90?'c_weekday':'';
                    var holiday=data[cn].jia>90?'c_holiday':'';

                    if(week>j+1&&i==0){
                        td+=`<td>
                        <div class="c_preMonth ${weekday+' '+holiday}">
                            <span>${lastEndDay-week+1+1+j}</span>
                            <span>${festival}</span>
                        </div>
                    </td>`
                    }else if(i*7+j+1>week-1+curEndDay){
                        td+=`<td>
                        <div class="c_nextMonth ${weekday+' '+holiday}">
                            <span>${i*7+j+1-week+1-curEndDay}</span>
                            <span>${festival}</span>
                        </div>
                    </td>`
                    }else{
                        var cl='';
                        var curStartDate=i*7+j+1-week+1;
                        // if(i*7+j+1-week+1==d.getDate()){
                        //     cl+=' '+'c_active';
                        // }
                        if(new Date().getFullYear()==d.getFullYear()&&new Date().getMonth()==d.getMonth()&&new Date().getDate()==d.getDate()&&new Date().getDate()==curStartDate){
                            cl+=' '+'c_today';

                            if(i*7+j+1-week+1==d.getDate()){
                                cl+=' '+'c_active';
                            }

                        }

                        td+=`<td>
                        <div class=" ${cl+' '+weekday+' '+holiday}">
                            <span>${i*7+j+1-week+1}</span>
                            <span>${festival}</span>
                        </div>
                    </td>`

                    // if(cl.indexOf('c_active')!=-1&&holiday=='c_holiday'){// 条件满足表示为节假日，启用绿色庆祝背景
                    //     holiday=='c_holiday'&&calendar.classList.add('c_active')
                    // }
                    }
                    tr.innerHTML=td;
                }
                tbody.appendChild(tr);
            }
            this.dateBindEvent();
            this.dateBE();
        },

        monthChange(){
            var arrows=document.querySelectorAll('.c_arrow');
            arrows[0].onclick=()=>{
                this.curDate.setMonth(this.curDate.getMonth()-1);
                this.renderSelect(this.curDate);
                this.getData(this.curDate);
                this.closeSelect();
            }
            arrows[1].onclick=()=>{
                this.curDate.setMonth(this.curDate.getMonth()+1);
                this.renderSelect(this.curDate);
                this.getData(this.curDate);
                this.closeSelect();
            }

        },
        backToday(){
            var returnBtn=document.querySelector('.c_top button');
            returnBtn.onclick=()=>{
                this.curDate=new Date();
                this.getData(this.curDate);
                this.renderSelect(this.curDate);

                // this.getData(new Date())
                // this.renderSelect(new Date());
            }
        },
        dateBindEvent(data){
            // var 
        },
        dateBE(){
            var dats=document.querySelectorAll('.c_date tbody td div');
            console.log(dats[0].innerHTML);
            dats.forEach((dat,index)=>{
                dat.onclick=ev=>{        
                    var dats=[...document.querySelectorAll('.c_date tbody td div')];
                    var act=dats.find(select=>select.classList.contains('c_active'));
                    act&&act.classList.remove('c_active');
                    dat.classList.add('c_active');
                    this.holiday();
                }
            }

            )
        },
        holiday(){
            var dats=document.querySelectorAll('.c_date tbody td div');
            var pd=0;
            var calendar=document.querySelector('#calendar');
            dats.forEach((dat)=>{
                if(dat.classList.contains('c_active')&&dat.classList.contains('c_holiday'))
                {
                    pd=1;
                    // console.log('1');
                    // calendar.classList.remove('c_active');
                }
                // else{
                //     pd=0;
                //     calendar.classList.add('c_active');
                // }
            }
            )
            if(pd==1){
                calendar.classList.add('c_active');
            }
            else{
                calendar.classList.remove('c_active');
            }
        }
    };
    calendar.init();
})();