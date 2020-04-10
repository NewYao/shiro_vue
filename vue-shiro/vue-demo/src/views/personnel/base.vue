<template>
<el-container>
    <el-header>
        <el-input clearable prefix-icon="el-icon-search" class="form_input" size="mini" v-model="searchData.fullname" placeholder="请输入姓名查询"></el-input>
        <el-input clearable prefix-icon="el-icon-phone" class="form_input" size="mini" v-model="searchData.phone" placeholder="请输入电话"></el-input>
        <el-input clearable prefix-icon="el-icon-chat-square" class="form_input" size="mini" v-model="searchData.email" placeholder="请输入邮箱"></el-input>
        <el-select v-model="searchData.state" placeholder="请选择人员状态" clearable size="mini">
            <el-option v-for="item in states" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
        </el-select>
        <el-date-picker style="margin-left:5px;" value-format="yyyy-MM-dd" size="mini" v-model="searchData.beginDateScope" type="daterange" align="right" unlink-panels range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions">
        </el-date-picker>
        <el-button @click="testClick" size="mini" type="primary">查询</el-button>
    </el-header>
    <el-main>
        <el-table  v-loading="loading" element-loading-text="拼命加载中"   :stripe="true" :highlight-current-row="true" size="mini" :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="index" :index="indexMethod" fixed="left"></el-table-column>
            <el-table-column type="selection" width="55" fixed="left">
            </el-table-column>
            <el-table-column prop="fullname" label="姓名" width="180" fixed="left">
            </el-table-column>
            <el-table-column prop="email" label="邮箱" width="180">
            </el-table-column>
            <el-table-column prop="phone" label="电话" width="180">
            </el-table-column>
            <el-table-column prop="createtime" label="日期">
            </el-table-column>
            <el-table-column prop="state" label="状态" width="180" :formatter="formatState">
            </el-table-column>
            <el-table-column label="操作" fixed="right" width="150">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row,1)" type="primary" :loading="false" size="mini">查看</el-button>
                    <el-button @click="handleClick(scope.row,2)" type="danger" :loading="false" size="mini">删除</el-button>
                    <el-button @click="handleClick(scope.row,3)" type="button" size="mini">编辑</el-button>
                </template>
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
            searchData: {
                fullname: null,
                phone: null,
                email: null,
                state: null,
                beginDateScope: null,
            },
            pickerOptions: {
                shortcuts: [{
                    text: '最近一周',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近一个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                        picker.$emit('pick', [start, end]);
                    }
                }, {
                    text: '最近三个月',
                    onClick(picker) {
                        const end = new Date();
                        const start = new Date();
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                        picker.$emit('pick', [start, end]);
                    }
                }]
            },
            states: [{
                    value: 1,
                    label: '正常'
                },
                {
                    value: 2,
                    label: '禁用'
                }
            ],
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
        /**表格前序号 */
        indexMethod(index) {
            return index + 1;
        },
        /**处理每列单独操作  1.查看，2删除，3编辑 */
        handleClick(row, tag) {
            console.log(row)
            if (1 == tag) {} else if (2 == tag) {
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest('/user/base/' + row.id).then(resp => {
                        var _this = this;
                        if (200 == resp.code) {
                            this.initList();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            } else if (3 == tag) {

            }
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
        /**格式化状态显示 */
        formatState(row, column, cellValue, index) {
            return 1 == cellValue ? "正常" : "禁用";
        },
        /**列表查询 */
        initList() {
            this.loading = true;
            let url = '/user/base/?page=' + this.currentPage + '&size=' + this.pageSize;
            if (this.searchData.fullname) {
                url += "&fullname=" + this.searchData.fullname;
            }
            if (this.searchData.phone) {
                url += "&phone=" + this.searchData.phone;
            }
            if (this.searchData.email) {
                url += "&email=" + this.searchData.email;
            }
            if (this.searchData.state) {
                url += "&state=" + this.searchData.state;
            }
            if (this.searchData.beginDateScope) {
                url += "&beginDateScope=" + this.searchData.beginDateScope;
            }
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

.el-header {
    /* height: 60px; */
    padding: 0px;
    display: flex;
    justify-content: left;
    align-items: center;
}

.el-header .form_input {
    width: 150px;
    margin-right: 5px;
}

.el-header .el-button {
    margin-left: 5px;
}

table .el-button--mini {
    padding: 3px 4px;
    margin: 2px;
}
</style>
