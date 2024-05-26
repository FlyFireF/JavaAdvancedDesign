var path = $("#path").val();
type = ['','info','success','warning','danger'];
var series_month=[]
var labels_month=[]

var series_bar=[]
var labels_bar=[]

var series_pie=[]
var labels_pie=[]

$.ajax({
    url: path+'/sys/main/linechart.json',
    type: 'GET',
    dataType: 'json',
    success: function(data) {
        Object.keys(data).forEach(function(key) {
            var sales = data[key];
            labels_month.push(key);
            series_month.push(sales);
        });
        function compareDates(date1, date2) {
            const [year1, month1] = date1.split('.');
            const [year2, month2] = date2.split('.');
            if (year1 !== year2) {
                return year1 - year2;
            } else {
                return month1 - month2;
            }
        }
        const sortedIndices = labels_month.map((_, index) => index).sort((index1, index2) => compareDates(labels_month[index1], labels_month[index2]));
        const sorted_labels_month = sortedIndices.map(index => labels_month[index]);
        const sorted_series_month = sortedIndices.map(index => series_month[index]);
        /*// 按照月份顺序对labels_month进行排序
        labels_month.sort(function(a, b) {
            var partsA = a.split('.');
            var partsB = b.split('.');

            if (partsA[0] !== partsB[0]) {
                return partsA[0] - partsB[0];
            } else {
                return partsA[1] - partsB[1];
            }
        });
        series_month = labels_month.map(function(label, index) {
            return series_month[index];
        });*/
        var data1 = {
            labels: sorted_labels_month,
            series: [
                sorted_series_month
            ]
        };

        var options = {
            seriesBarDistance: 10,
            axisX: {
                showGrid: false
            },
            height: "245px"
        };

        var responsiveOptions = [
            ['screen and (max-width: 640px)', {
                seriesBarDistance: 5,
                axisX: {
                    labelInterpolationFnc: function (value) {
                        return value[0];
                    }
                }
            }]
        ];
        console.log(data1)
        Chartist.Line('#chartActivity', data1, options, responsiveOptions);

    },
    error: function(jqXHR, textStatus, errorThrown) {
        console.error('Error fetching data: ' + textStatus, errorThrown);
    }
});

$.ajax({
    url: path+'/sys/main/barchart.json',
    type: 'GET',
    dataType: 'json',
    success: function(data) {
        Object.keys(data).forEach(function(key) {
            var sales = data[key];
            labels_bar.push(key);
            series_bar.push(sales);
        });

        //      bar chart
        var dataViews = {
            labels: labels_bar,
            series: [
                series_bar
            ]
        };

        var optionsViews = {
            seriesBarDistance: 10,
            classNames: {
                bar: 'ct-bar'
            },
            axisX: {
                showGrid: false,

            },
            height: "250px"

        };

        var responsiveOptionsViews = [
            ['screen and (max-width: 640px)', {
                seriesBarDistance: 5,
                axisX: {
                    labelInterpolationFnc: function (value) {
                        return value[0];
                    }
                }
            }]
        ];
        console.log(dataViews)
        Chartist.Bar('#chartViews', dataViews, optionsViews, responsiveOptionsViews);
    },
    error: function(jqXHR, textStatus, errorThrown) {
        console.error('Error fetching data: ' + textStatus, errorThrown);
    }
});



$.ajax({
    url: path+'/sys/main/piechart.json',
    type: 'GET',
    dataType: 'json',
    success: function(data) {
        Object.keys(data).forEach(function(key) {
            var sales = data[key];
            var percentage = (sales.toFixed(2)) + '%';
            var label = key + '-' + percentage;

            labels_pie.push(label);
            series_pie.push(sales);
        });


        //      pie chart
        Chartist.Pie('#chartPreferences', {
            labels: labels_pie,
            series: series_pie
        });

    },
    error: function(jqXHR, textStatus, errorThrown) {
        console.error('Error fetching data: ' + textStatus, errorThrown);
    }
});

demo = {
    initPickColor: function(){
        $('.pick-class-label').click(function(){
            var new_class = $(this).attr('new-class');
            var old_class = $('#display-buttons').attr('data-class');
            var display_div = $('#display-buttons');
            if(display_div.length) {
            var display_buttons = display_div.find('.btn');
            display_buttons.removeClass(old_class);
            display_buttons.addClass(new_class);
            display_div.attr('data-class', new_class);
            }
        });
    },

}
