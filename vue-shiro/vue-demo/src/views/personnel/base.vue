<template>
<el-container>
    <el-header>
        <el-button @click="testClick" type="button">查询</el-button>
        1111111111111111
    </el-header>
    <el-main>
        <el-table v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.8)" :stripe="true" :highlight-current-row="true" size="mini" :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="index" :index="indexMethod"></el-table-column>
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="fullname" label="姓名" width="180">
            </el-table-column>
            <el-table-column prop="email" label="邮箱" width="180">
            </el-table-column>
            <el-table-column prop="phone" label="电话" width="180">
            </el-table-column>
            <el-table-column prop="createtime" label="日期">
            </el-table-column>
            <el-table-column prop="state" label="状态" width="180">
            </el-table-column>
        </el-table>
        <el-pagination :background="true" @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" :page-sizes="[10,15,20,30,50,100]" :page-size="10" layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
    </el-main>

</el-container>
</template>

<script>
import {
    Message
} from 'element-ui';
export default {
    name: 'per_base',
    data() {
        return {
            msg: '人员列表！',
            currentPage: 1,
            pageSize: 10,
            total: 0,
            loading: false,
            multipleSelection: [],
            tableData: []
        }
    },
    mounted() {
        this.initList();
    },
    methods: {
        testClick() {
            this.initList();
        },
        indexMethod(index) {
            return index + 1;
        },
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
            console.log(this.multipleSelection)
        },
        dateFilter(value, row, column) {
            const property = column['property'];
            return row[property] == value;
        },
        handleSizeChange(toPageSize) {
            this.pageSize = toPageSize;
            this.initList();
        },
        handleCurrentChange(toPageNum) {
            this.currentPage = toPageNum;
            this.initList();
        },
        initList() {
            this.loading = true;
            let url = '/user/base/?page=' + this.currentPage + '&size=' + this.pageSize;
            this.getRequest(url, '').then(resp => {
                var _this = this;
                _this.loading = false;
                _this.tableData = resp.data.rows;
                _this.total = resp.data.total;
                _this.currentPage = resp.data.pageNum;
            })
        }

    },
}
</script>

<style scoped>
.el-pagination {
    margin-top: 10px;
}

.el-main {
    padding: 0 20px 0 0;
}
</style>
