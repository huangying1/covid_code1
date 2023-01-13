$.getJSON("http://127.0.0.1:8080/static/data/user-location.json", d => {
    console.log(d)
    data = d.map(v => ({name: v[0], value: v[1]}));

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('location'), 'dark');
    var geoCoordMap = {
        '甘肃':[103.73, 36.03],
        '青海':[101.74, 36.56],
        '四川':[104.06, 30.67],
        '河北':[114.48, 38.03],
        '云南':[102.73, 25.04],
        '贵州':[106.71, 26.57],
        '湖北':[114.31, 30.52],
        '河南':[113.65, 34.76],
        '山东':[117, 36.65],
        '江苏':[118.78, 32.04],
        '安徽':[117.27, 31.86],
        '浙江':[120.19, 30.26],
        '江西':[115.89, 28.68],
        '福建':[119.3, 26.08],
        '广东':[113.23, 23.16],
        '湖南':[113, 28.21],
        '海南':[110.35, 20.02],
        '辽宁':[123.38, 41.8],
        '吉林':[125.35, 43.88],
        '黑龙江':[126.63, 45.75],
        '山西':[112.53, 37.87],
        '陕西':[108.95, 34.27],
        '台湾':[121.30, 25.03],
        '北京':[116.46, 39.92],
        '上海':[121.48, 31.22],
        '重庆':[106.54, 29.59],
        '天津':[117.2, 39.13],
        '内蒙古':[111.65, 40.82],
        '广西':[108.33, 22.84],
        '西藏':[91.11, 29.97],
        '宁夏':[106.27, 38.47],
        '新疆':[87.68, 43.77],
        '香港':[114.17, 22.28],
        '澳门':[113.54, 22.19],
        '其他':0,
    };

var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value)
            });
        }
    }
    return res;
};
option = {
    backgroundColor: '#483d8b',
    title : {text: '用户分布',},
    tooltip : {
        trigger: 'item',
        formatter: function(params) {
                if (typeof(params.value)[2] == "undefined") {
                    return params.name + ' : ' + params.value;
                } else {
                    return params.name + ' : ' + params.value[2];
                }
         },
         textStyle: {
            color: '#FFF',
            fontSize:24
          }
    },
    legend: {
        show:false
    },
    geo: {
        map: 'china',
        label: {
            normal: {show: false},
            emphasis: {show: false}
        },
        roam: false,
        itemStyle: {
            normal: {
                areaColor: 'rgba(0,153,255,0.6)',
                borderColor: '#09F'
            },
            emphasis: {
                areaColor: 'rgba(0,153,255,0.6)',
                borderColor: '#09F'
          }
    }
},
series: [{ //普通小点
    name: '战略布局点',
    type: 'scatter',
    coordinateSystem: 'geo',
    zlevel: 1,
    rippleEffect: {
    brushType: 'stroke'
    },
    symbolSize: function (val) {
        return val[2] / 96;
    },
    label: {
        normal: {show: false},
        emphasis: {show: false}
    },
    itemStyle: {
        normal: {color: 'rgba(255,255,0,0.8)'},
        emphasis: {color: 'rgba(246,33,87,1)'}
      },
    data: convertData(data)
},
{ //有扩散效果的大点
    name: '战略布局TOP5',
    type: 'effectScatter',
    coordinateSystem: 'geo',
    zlevel: 2,
    hoverAnimation: true,
    showEffectOn: 'render',
    rippleEffect: {
        brushType: 'stroke'
    },
    label: {
        normal: {
            show: true,
            fontFamily:'微软雅黑',
            fontSize: 24,
            color:'#FFF',
            formatter: '{b}',
            position: 'right',
            shadowBlur: 5,
           shadowColor: '#000'
    },
    emphasis: {
        show: true
    }
},
symbolSize: function (val) {
        return val[2] / 96;
},
itemStyle: {
        normal: {
            color: 'rgba(255,255,255,1)'
        }
},
data: convertData(data).sort(function(a, b) {
                    return b.value[2] - a.value[2];
                }).slice(0, 5)
},
{//有地理位置的标记，同时显示值
    name: '点',
    type: 'scatter',
    coordinateSystem: 'geo',
    symbol: 'pin',
    symbolSize: function(val) {
        return val[2]/32;
    },
    label: {
        normal: {
            show: true,
            fontFamily:'Arial, Helvetica, sans-serif',
            formatter:'{@[2]}',
            textStyle: {
                color: '#FFF',
                fontSize: 18,
            }
        }
},
itemStyle: {
        normal: {
            color: 'rgba(246,33,87,1)', //标志颜色
        }
},
zlevel: 3,
data: convertData(data).sort(function(a, b) {
                    return b.value[2] - a.value[2];
                }).slice(0, 5)
}]
};

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
})

