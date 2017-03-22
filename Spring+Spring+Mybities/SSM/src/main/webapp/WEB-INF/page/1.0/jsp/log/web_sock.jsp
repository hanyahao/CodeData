<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="p30">
    <div class="main-widgets">
        <div class="main-widgets-title">
            <span>WebSocket请求</span>
        </div>
        <form class="main-widgets-form">
            <label>查询</label>
            <input type="text" name="keywords" value="" placeholder="请输入关键词" class="ml10 w350" />
            <input type="text" name="start_date" value="" class="jui-date ml10" />
            <input type="text" name="end_date" value="" class="jui-date ml10" />
            <button class="submit ml10">搜 索</button>
        </form>
        <div class="main-list">
            <table>
                <tr class="list-head">
                    <th class="list-left w120">终端编号</th>
                    <th class="list-left w150">命令码</th>
                    <th class="list-left">命令序列号</th>
                    <th class="list-left w450">完整请求消息</th>
                    <th class="list-left w150">创建时间</th>
                </tr>
            </table>
        </div>
        <div class="main-list-pages"></div>
    </div>
</div>

<script type="text/javascript">
	Websock.listInit();
</script>