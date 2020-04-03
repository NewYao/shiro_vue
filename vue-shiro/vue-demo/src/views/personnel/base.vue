<template>
<el-container>
    <el-header>
        <el-button type="button">查询</el-button>
        1111111111111111
    </el-header>
    <el-main>
        <el-table size="mini" :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="date" label="日期" width="180" :filters="dataArr" :filter-method="dateFilter">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="180">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
        </el-table>
        <el-pagination background layout="prev, pager, next" :total="100" :hide-on-single-page="true">
        </el-pagination>
    </el-main>

</el-container>
</template>

<script>
export default {
    name: 'per_base',
    data() {
        return {
            msg: '人员列表！',
            multipleSelection: [],
            dataArr: [{
                text: '2016-05-01',
                value: '2016-05-01'
            }, {
                text: '2016-05-02',
                value: '2016-05-02'
            }, {
                text: '2016-05-03',
                value: '2016-05-03'
            }, {
                text: '2016-05-04',
                value: '2016-05-04'
            }],
            tableData: [{
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                date: '2016-05-04',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1517 弄'
            }, {
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1519 弄'
            }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄'
            }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄'
            }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄'
            }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄'
            }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄'
            }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄'
            }, {
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1516 弄'
            }]
        }
    },
    methods: {
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
        }
    },
}
</script>

<style scoped>
.el-pagination {
    margin-top: 10px;
}
.el-main{
    padding: 0 20px 0 0;
}
</style>
