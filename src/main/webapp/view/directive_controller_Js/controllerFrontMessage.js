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
    // $scope.tableParams = new NgTableParams({}, { dataset: data});
    /*
     count
     data
     filter
     generatePagesArray
     group
     hasErrorState
     hasFilter
     hasFilterChanges
     hasGroup
     isDataReloadRequired
     isSortBy
     orderBy
     page
     parameters
     reload
     reloadPages
     settings
     sorting
     total
     url
     */
    $scope.tableParams = new NgTableParams({count:5}, { getData:function (params) {
        var count=params.count();
        // var data=params.data();
        var filter=params.filter();
        var generatePagesArray=params.generatePagesArray();
        var group=params.group();
        var hasErrorState=params.hasErrorState();
        var hasFilter=params.hasFilter();
        var hasGroup=params.hasGroup();
        var isDDataReloadRequired=params.isDataReloadRequired();
        var isSortBy=params.isSortBy();
        var orderBy=params.orderBy();
        var page=params.page();
        var parameters=params.parameters();
        // var reload=params.reload();
        // var reloadPages=params.reloadPages();
        var settings=params.settings();
        var sorting=params.sorting();
        var total=params.total();
        var url=params.url();
        return data;
    }});
})