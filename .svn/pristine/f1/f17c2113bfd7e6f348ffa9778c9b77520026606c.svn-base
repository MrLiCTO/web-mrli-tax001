<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Calendar cd=Calendar.getInstance();
	int curYear=cd.get(Calendar.YEAR);
	request.setAttribute("curYear",curYear);
	List yearList=new ArrayList();
	
	for(int i=curYear;i>=(curYear-4);i--){
		yearList.add(i);
	}
	
	request.setAttribute("yearList",yearList);
	
	
%>

<!DOCTYPE HTML>
<html>
  <head>
    <%@include file="/common/header.jsp"%>
    <title>年度投诉统计图</title>
  </head>
  <script type="text/javascript" src="${basePath}js/fusioncharts/fusioncharts.js"></script>
   <script type="text/javascript" src="${basePath}js/fusioncharts/fusioncharts.charts.js"></script>
    <script type="text/javascript" src="${basePath}js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>
  <script type="text/javascript">
  $(document).ready(doAnnualStatistic());
  
  	function doAnnualStatistic(){
  	    // Create a new instance of FusionCharts for rendering inside an HTML
	    // `<div>` element with id `my-chart-container`.
	    var year=$("#year option:selected").val();
	    
	    if(year==""||year==undefined){
	    	year="${curYear}";
	    }
	    
	    $.ajax({
	    	type:"post",
	    	url:"${basePath}nsfw/complain_getAnnualStatisticData.action",
	    	data:{
	    			"year":year
	    		},
	    	dataType:"json",
	    	success:function(data){
	    		if(data!=null&&data!=""&&data!=undefined){
	    			 var myChart = new FusionCharts({
	    			        "type": "line",
	    			        "renderAt": "chartContainer",
							"width": "600",
							"height":"400",
	    			        "dataFormat": "json",
	    			        "dataSource": {
	    			            "chart": {
	    			                "caption": year+" 年年度投诉数统计图",
	    			    			"xAxisName":"投 诉 数",
	    			    			"yAxisName":"月 份",
	    			    			"theme":"fint"
	    			            },
	    			            "data":data.chartData
	    			        }
	    			    });
	    			 /* if(data != null && data != "" && data != undefined){
	    					  var revenueChart = new FusionCharts({
	    					        "type": "line",
	    					        "renderAt": "chartContainer",
	    					        "width": "600",
	    					        "height": "400",
	    					        "dataFormat": "json",
	    					        "dataSource":  {
	    					          "chart": {
	    					            "caption": year + " 年度投诉数统计图",
	    					            "xAxisName": "月  份",
	    					            "yAxisName": "投  诉  数",
	    					            "theme": "fint"
	    					         },
	    					         "data": data.chartData
	    					      }

	    					  });*/

	    			    // Render the chart.
	    			myChart.render();
	    		}else{
	    			alert("操作失败")
	    		}
	    	},
	    	error:function(){alert("操作失败");}
	    	
	    	});
	    
	   
  	}
  
  </script>
  
  <body>
  	<br>
    <s:select id="year" list="#request.yearList" onchange="doAnnualStatistic()"></s:select>
    <br>
    <div id="chartContainer" style="text-align:center;width:100%;"></div>
  </body>
</html>
