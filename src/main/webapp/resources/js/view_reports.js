
	function breakupChartGroupView(chartData) {

	    // Make monochrome colors and set them as default for all pies
//	    Highcharts.getOptions().plotOptions.pie.colors = (function () {
//	        var colors = [],
//	            base = Highcharts.getOptions().colors[0],
//	            i;
//
//	        for (i = 0; i < 10; i += 1) {
//	            // Start out with a darkened base color (negative brighten), and end
//	            // up with a much brighter color
//	            colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
//	        }
//	        return colors;
//	    }());
		
		   Highcharts.setOptions({
		        colors: ['#B53F53', '#397B93', '#518A52']
		    });
	    
	$('#breakup').highcharts({
		  chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	            type: 'pie'
	        },
	        credits: {
	            enabled: false
	        },
	        title: {
	            text: 'Migration Status - Breakup',
	            style:{
                    color:'#264F6C'
                }
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                    style: {
	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                    }
	                }
	            }
	        },
	        series: [{
	            name: "Status",
	            colorByPoint: true,
	            data: (function() {
	                // generate an array of random data
	                var data = [], i;
	                if(chartData.uncommitted != null){data.push({
                        name : "Uncommitted",
                        y: Number(chartData.uncommitted)
                    });} else {
                    	data.push({
                            name : "Uncommitted",
                            y: 0
                        });
                    }
	                
	                if(chartData.committed != null){data.push({
                        name : "Committed",
                        y: Number(chartData.committed)
                    });} else {
                    	data.push({
                            name : "Committed",
                            y: 0
                        });
                    }
//	                if(chartData.in_progress != null){data.push({
//                        name : "In Progress",
//                        y: Number(chartData.in_progress)
//                    });} else {
//                    	data.push({
//                            name : "In Progress",
//                            y: 0
//                        });
//                    }
	                if(chartData.completed != null){data.push({
                        name : "Completed",
                        y: Number(chartData.completed)
                    });} else {
                    	data.push({
                            name : "Completed",
                            y: 0
                        });
                    }
	                
	                return data;
	            })()
	        }]
    });
}
	
	function createPlannedMigChart(chartData) {
		
		var data = [];
		var svp = [];
		var uncommitted = [];
		var committed = [];
	//	var in_progress = [];
		var completed = [];

		for(var nodeData in chartData){
				
				svp.push(chartData[nodeData].svp);
				uncommitted.push(Number(chartData[nodeData].uncommitted));
				committed.push(Number(chartData[nodeData].committed));
		//		in_progress.push(Number(chartData[nodeData].in_progress));
				completed.push(Number(chartData[nodeData].completed));
		}
		data.push({
			name : 'Uncommitted',
			data : uncommitted
		});
		data.push({
			name : 'Committed',
			data : committed
		});
//		data.push({
//			name : 'In_Progress',
//			data : in_progress
//		});
		data.push({
			name : 'Completed',
			data : completed
		});
		
	    $('#planned_mig').highcharts({
	        chart: {
	            type: 'column'
	        },
	        credits: {
	            enabled: false
	        },
	        title: {
	            text: 'Migration Planned - Grouped by Senior Staff'
	        },
	        xAxis: {
	            categories: svp
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: 'Status'
	            },
	            stackLabels: {
	                enabled: true,
	                style: {
	                    fontWeight: 'bold',
	                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                }
	            }
	        },
	        legend: {
	            align: 'right',
	            x: -30,
	            verticalAlign: 'top',
	            y: 25,
	            floating: true,
	            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
	            borderColor: '#CCC',
	            borderWidth: 1,
	            shadow: false
	        },
	        tooltip: {
	            headerFormat: '<b>{point.x}</b><br/>',
	            pointFormat: '{series.name}: {point.y}<br />',
	            shared: true
	        },
	        plotOptions: {
	            column: {
	                stacking: 'normal',
	                dataLabels: {
	                    enabled: true,
	                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
	                    style: {
	                        textShadow: '0 0 3px black'
	                    }
	                }
	            }
	        },
	        colors: ['#B53F53', '#397B93', '#518A52'],
	        series: data
	    });
	}
	


	function breakupChart(chartData) {
		
	    // Make monochrome colors and set them as default for all pies
//	    Highcharts.getOptions().plotOptions.pie.colors = (function () {
//	        var colors = [],
//	            base = Highcharts.getOptions().colors[0],
//	            i;
//
//	        for (i = 0; i < 10; i += 1) {
//	            // Start out with a darkened base color (negative brighten), and end
//	            // up with a much brighter color
//	            colors.push(Highcharts.Color(base).brighten((i - 4) / 7).get());
//	        }
//	        return colors;
//	    }());
	    
		Highcharts.setOptions({
	        colors: ['#B53F53', '#397B93', '#518A52']
	    });
	    
	$('#overall_breakup').highcharts({
		  chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	            type: 'pie'
	        },
	        credits: {
	            enabled: false
	        },
	        title: {
	            text: 'CCIX to LAE Migration Status',
	            style:{
                    color:'#264F6C'
                }
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                    style: {
	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                    }
	                }
	            }
	        },
	        series: [{
	            name: "Status",
	            colorByPoint: true,
	            data: (function() {
	                // generate an array of random data
	                var data = [], i;
	                
	                if(chartData.uncommitted != null){data.push({
                        name : "Uncommitted",
                        y: Number(chartData.uncommitted)
                    });} else {
                    	data.push({
                            name : "Uncommitted",
                            y: 0
                        });
                    }
	                
	                if(chartData.committed != null){data.push({
                        name : "Committed",
                        y: Number(chartData.committed)
                    });} else {
                    	data.push({
                            name : "Committed",
                            y: 0
                        });
                    }
//	                if(chartData.in_progress != null){data.push({
//                        name : "In Progress",
//                        y: Number(chartData.in_progress)
//                    });} else {
//                    	data.push({
//                            name : "In Progress",
//                            y: 0
//                        });
//                    }
	                if(chartData.completed != null){data.push({
                        name : "Completed",
                        y: Number(chartData.completed)
                    });} else {
                    	data.push({
                            name : "Completed",
                            y: 0
                        });
                    }
	                
	                return data;
	            })()
	        }]
    });
}
	
	
	function breakupChartOfferingView(chartData) {

	    // Make monochrome colors and set them as default for all pies
//	    Highcharts.getOptions().plotOptions.pie.colors = (function () {
//	        var colors = [],
//	            base = Highcharts.getOptions().colors[0],
//	            i;
//
//	        for (i = 0; i < 10; i += 1) {
//	            // Start out with a darkened base color (negative brighten), and end
//	            // up with a much brighter color
//	            colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
//	        }
//	        return colors;
//	    }());
		
		Highcharts.setOptions({
	        colors: ['#B53F53', '#397B93', '#518A52']
	    });
	    
	$('#breakup_offering').highcharts({
		  chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	            type: 'pie'
	        },
	        credits: {
	            enabled: false
	        },
	        title: {
	            text: 'CCIX-LAE Migration Status',
	            style:{
                    color:'#264F6C'
                }
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                    style: {
	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                    }
	                }
	            }
	        },
	        series: [{
	            name: "Status",
	            colorByPoint: true,
	            data: (function() {
	                // generate an array of random data
	                var data = [], i;
	                if(chartData.uncommitted != null){data.push({
                        name : "Uncommitted",
                        y: Number(chartData.uncommitted)
                    });} else {
                    	data.push({
                            name : "Uncommitted",
                            y: 0
                        });
                    }
	                
	                if(chartData.committed != null){data.push({
                        name : "Committed",
                        y: Number(chartData.committed)
                    });} else {
                    	data.push({
                            name : "Committed",
                            y: 0
                        });
                    }
//	                if(chartData.in_progress != null){data.push({
//                        name : "In Progress",
//                        y: Number(chartData.in_progress)
//                    });} else {
//                    	data.push({
//                            name : "In Progress",
//                            y: 0
//                        });
//                    }
	                if(chartData.completed != null){data.push({
                        name : "Completed",
                        y: Number(chartData.completed)
                    });} else {
                    	data.push({
                            name : "Completed",
                            y: 0
                        });
                    }
	                
	                return data;
	            })()
	        }]
    });
}
	