(function(){
    var jiejia={
        curDate:new Date(),
        init(){
            this.zuo(this.curDate);
            this.you(this.curDate);
        },
        getDateList(d){
            var dataList;
            $.get('http://api.tianapi.com/jiejiari/index',
            {
                key:'c5c3fc5bd86ad318f0f3ac5c1379209e',type:'1',date:`${d.getFullYear()}`
            },
            function(data,status){
                dataList=data.newslist;
                console.log(dataList);
                renderLeft(d,dataList);
            });
            console.log(dataList);
        },
        zuo(d){
            var counterp=document.querySelector('.countdown .weekendDistance p');
            var counterdiv=document.querySelector('.weekendDistance div');
            var date=document.querySelector('.j_date');
            date.innerHTML=(this.curDate.getFullYear()+'-'+(this.curDate.getMonth()+1)+'-'+this.curDate.getDate())
            if(this.curDate.getDay()==0||this.curDate.getDay()==6)
            {
                counterp.innerHTML='周末到辣';
                counterdiv.innerHTML='😊';
            }
            else
            {
                var i;
                for(i=0;this.curDate.getDay()+i!=6;i++);
                counterdiv.innerHTML=`${i}天`;
            }
            var dataList;
            $.get('http://api.tianapi.com/jiejiari/index',
            {
                key:'c5c3fc5bd86ad318f0f3ac5c1379209e',type:'1',date:`${d.getFullYear()}`
            },
            function(data,status){
                dataList=data.newslist;
                var bool=0;
                // console.log(dataList)
                for(var i=0;i<dataList.length;i++)
                {
                    var begin=dataList[i].vacation.replace(/\|....-..-../g,'');
                    var end=dataList[i].vacation.replace(/....-..-..\|/g,'');
                    var beginYear=begin.replace(/-..-../g,'');
                    var endYear=end.replace(/-..-../g,'');
                    var beginMonth=begin.replace(/....-/g,'').replace(/-../g,'');
                    var endMonth=end.replace(/....-/g,'').replace(/-../g,'');
                    var beginDate=begin.replace(/....-..-/g,'');
                    var endDate=end.replace(/....-..-/g,'');
                    if((beginMonth==d.getMonth()+1&&beginDate>=d.getDate())||beginMonth-1>d.getMonth())
                    {
                        bool=1;
                        break;
                    }
                }
                if(bool==0){


                    $.get('http://api.tianapi.com/jiejiari/index',
                    {
                        key:'c5c3fc5bd86ad318f0f3ac5c1379209e',type:'1',date:`${d.getFullYear()+1}`
                    },
                    function(data,status){
                        dataList=data.newslist;
                        console.log(d.getFullYear()+1)
                        // var begin=dataList[0].vacation.replace(/\|....-..-../g,'');
                        // var end=dataList[0].vacation.replace(/....-..-..\|/g,'');
                        // var beginYear=begin.replace(/-..-../g,'');
                        // var endYear=end.replace(/-..-../g,'');
                        // var beginMonth=begin.replace(/....-/g,'').replace(/-../g,'');
                        // var endMonth=end.replace(/....-/g,'').replace(/-../g,'');
                        // var beginDate=begin.replace(/....-..-/g,'');
                        // var endDate=end.replace(/....-..-/g,'');
                        // var holidayName=document.querySelector('.holidayLeft p');
                        // var holidayCounter=document.querySelector('.cdHoliday');
                        // var holidayduring=document.querySelector('.during');
                        // holidayName.innerHTML=dataList[0].name;
                        // var holiday=new Date();
                        // holiday.setDate(beginDate);
                        // holiday.setMonth(beginMonth-1);
                        // holiday.setYear(beginYear);
                        // holidayCounter.innerHTML=parseInt((holiday-d)/3600/1000/24)+'天'
                        // holidayduring.innerHTML=beginMonth+'月'+beginDate+'日-'+endMonth+'月'+endDate+'日';
                    });


                }
                else{
                    var holidayName=document.querySelector('.holidayLeft p');
                    holidayName.innerHTML=dataList[i].name;
                    var holidayName=document.querySelector('.holidayLeft p');
                    var holidayCounter=document.querySelector('.cdHoliday');
                    var holidayduring=document.querySelector('.during');
                    holidayName.innerHTML=dataList[i].name;
                    var holiday=new Date();
                    holiday.setDate(beginDate);
                    holiday.setMonth(beginMonth-1);
                    holiday.setYear(beginYear);
                    holidayCounter.innerHTML=parseInt((holiday-d)/3600/1000/24)+'天';
                    holidayduring.innerHTML=beginMonth+'月'+beginDate+'日-'+endMonth+'月'+endDate+'日';
                }
            });
        },
        you(d){
            var holidayDisplayp=document.querySelectorAll('.holidayDisplay p');
            var holidayDisplaydiv=document.querySelectorAll('.holidayDate');
            var holidayXiu=document.querySelectorAll('.holidayList .xiu');
            var progress=0;
                $.ajax({
                    url: `https://www.rili.com.cn/rili/json/pc_wnl/${d.getFullYear()}/${d.getMonth()+1}.js`,
                    dataType: 'jsonp',
                });
                window.jsonrun_PcWnl=res=>{
                    for(var i=0;i<42&&progress<4;i++)
                    {
                        if(res.data[i].yuethis!=-1&&res.data[i].yue>=this.curDate.getMonth()&&res.data[i].ri>=this.curDate.getDate()&&res.data[i].jie.replace(/<.+?>/g,'')!="")
                        {
                            holidayDisplaydiv[progress].innerHTML=res.data[i].yue+'月'+res.data[i].ri+'日';
                            holidayDisplayp[progress++].innerHTML=res.data[i].jie.replace(/<.+?>/g,'');
                            if(res.data[i].jia>90){
                                holidayXiu[progress-1].classList.add('active');
                            }
                        }
                    }
                    if(progress<4){
                        d.setMonth(d.getMonth()+1);
                        $.ajax({
                            url: `https://www.rili.com.cn/rili/json/pc_wnl/${d.getFullYear()}/${d.getMonth()+1}.js`,
                            dataType: 'jsonp',
                        });
                        window.jsonrun_PcWnl=res=>{
                            for(var i=0;i<42&&progress<4;i++)
                            {
                                if(res.data[i].yuethis!=-1&&res.data[i].jie.replace(/<.+?>/g,'')!="")
                                {
                                    holidayDisplaydiv[progress].innerHTML=res.data[i].yue+'月'+res.data[i].ri+'日';
                                    holidayDisplayp[progress++].innerHTML=res.data[i].jie.replace(/<.+?>/g,'');
                                    if(res.data[i].jia>90){
                                        holidayXiu[progress-1].classList.add('active');
                                    }
                                }
                            }
                        };
                        d.setMonth(d.getMonth()-1);
                    }
                };
        }
        
    }
    jiejia.init();
})();