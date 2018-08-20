
//webNewslist
$(function () {
    $('#newList').dataTable({
        "aaSorting": [[1, "desc"]],//默认第几个排序
        "bStateSave": true,//状态保存
        "pading": false,
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {
                "orderable": false,
                "aTargets": [0, 8]
            } // 不参与排序的列
        ]
    });

    $('input[name="chkWebNews"]','#newList').click(function()
    {
    	getCheckedIds();
    });
    
    function getCheckedIds()
    {
    	var chkObjs=$('input[name="chkWebNews"]','#newList');
		var ids = '',
		count=0,
		len = chkObjs.length;
		for (var i = 0; i < len; i++)
		{
			if ($(chkObjs[i]).prop('checked'))
			{
				ids += ',' + $(chkObjs[i]).val();
				count++;
			}
		}
		
		if(count==len)
		{
			$('#chkAll').prop('checked',true);	
		}else{
			$('#chkAll').prop('checked',false);	
		}
		
		ids = ids.substring(1, ids.length);		
		$('#chkAll').val(ids);   	
    }
    
    $('#chkAll').click(function()
    {
    	if($(this).prop('checked'))
    	{
    		$('input[name="chkWebNews"]','#newList').prop('checked',true);
    		getCheckedIds();
    	}else{
    		$('input[name="chkWebNews"]','#newList').prop('checked',false);
    		$('#chkAll').val('');
    	}
    });
    
    $('#btnMultiDelete').click(function(){
    
    	var ids=$('#chkAll').val();
    	if(!ids){
    		layer.msg('请至少选中一条记录', {icon: 5});
    		return;
    	}
    	
    	layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: sitePath+'/Admin/multiDeleteWebNews.do?ids=' + ids,
                success: function (data) {
                    layer.msg('已删除!', {
                        icon: 1,
                        time: 1000
                    },function(){
                    	 $("#btnRefreshList").trigger('click'); 	
                    });
                   
                },
                error: function (data) {
                    console.log(data);
                },
            });
        });
    	
    });
    
    $('#btnSearch').click(function () {
        var title = $('#searchTitle').val();
        var keywords = $('#searchKeywords').val();
        var author = $('#searchAuthor').val();
        var content = $('#searchContent').val();
        var mId = $('#hidMId').val();

        $.ajax({
            type: 'post',
            url: sitePath+'/Admin/searchWebNews.do',
            data: {
                title: title,
                keywords: keywords,
                author: author,
                content: content,
                menuId: mId
            },
            dataType: 'json',
            success: function (ret) {
                $('#newList>tbody').html('');
                var html = '<tr><td colspan="9">没有数据</td></tr>';
                if (ret && ret.length > 0) {
                    html = '';
                    for (var i = ret.length; i > 0; i--) {
                        var obj = ret[i - 1];
                        html += '<tr>';
                        html += '<td><input type="checkbox" value="" name=""></td>';
                        html += '<td>' + i + '</td>';
                        html += '<td>' + obj.title + '</td>';
                        html += '<td>' + obj.keywords + '</td>';
                        html += '<td>' + obj.author + '</td>';
                        html += '<td>' + obj.brief + '</td>';
                        html += '<td>' + obj.addon+ '</td>';
                        html += '<td>' + obj.readCount + '</td>';
                        html += '<td>已发布</td>';
                        html += '<td class="operate">';
                        html += '<i class="fa fa-edit"  onclick="article_edit('+ obj.id + ',' + obj.mId + ')"></i> ';
                        html += '<i class="fa fa-trash-o" onclick="article_del('+ obj.id + ')"></i>';
                        html += '</td>';
                        html += '</tr>';
                    }

                }

                $('#newList>tbody').html(html);

            },
            error: function () {

            }
        });
    });
});

function article_add(url) {
    $("#myModal").modal('show');
    $("#myModalTitle").html('添加内容');
    $("#myModalContent").load(url);
}

function article_edit(id, mId) {
    var url = sitePath+'/Admin/webNews.do?id=' + id + '&mId=' + mId;
    $("#myModal").modal('show');
    $("#myModalTitle").html('编辑内容');
    $("#myModalContent").load(url);
}

function article_del(id) {

    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: sitePath+'/Admin/deleteWebNews.do?id=' + id,
            success: function (data) {
                layer.msg('已删除!', {
                    icon: 1,
                    time: 1000
                },function(){
                	$("#btnRefreshList").trigger('click');
                });
                
            },
            error: function (data) {
                console.log(data);
            },
        });
    });
}

