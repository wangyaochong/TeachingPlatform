app.controller("controllerFrontMessage",function ($scope,NgTableParams) {
    var data = [{name: "Moroni", age: 50},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18},
        {name:"hello",age:18}
        /*,*/];
    $scope.tableParams = new NgTableParams({}, { dataset: data});
    // $scope.tableParams = new NgTableParams({}, { getData:function (params) {
    //     var urltmp=params.url();
    //     return data;
    // }});
})