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
        <el-date-picker value-format="yyyy-MM-dd" size="mini" v-model="searchData.beginDateScope" type="daterange" align="right" unlink-panels range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions">
        </el-date-picker>

        <el-button @click="testClick" size="mini" type="primary">查询</el-button>
        <el-button @click="openWindows" size="mini" type="success">新增用户</el-button>

    </el-header>
    <el-main>
        <el-table v-loading="loading" element-loading-text="拼命加载中" :stripe="true" :highlight-current-row="true" size="small" :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
            <el-table-column type="index" :index="indexMethod" fixed align="center"></el-table-column>
            <el-table-column type="selection" width="55" fixed align="center">
            </el-table-column>
            <el-table-column prop="fullname" label="姓名" fixed align="center">
            </el-table-column>
            <el-table-column prop="username" label="登录名" min-width="100" align="center">
            </el-table-column>
            <!-- <el-table-column prop="password" label="密码" align="center">
            </el-table-column> -->
            <el-table-column prop="email" label="邮箱" min-width="150" align="center">
            </el-table-column>
            <el-table-column prop="phone" label="电话" min-width="120" align="center">
            </el-table-column>
            <el-table-column prop="createtime" label="注册日期" width="150" align="center">
            </el-table-column>
            <el-table-column prop="state" label="状态" align="center">
                <template slot-scope="scope">
                    <span v-if="1 == scope.row.state" style='color:#67C23A'>活动</span>
                    <span v-else style='color:#909399'>禁用</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="200" align="center" fixed="right">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row,1)" type="primary" :loading="false" size="mini">查看</el-button>
                    <el-button v-if="scope.row.state == 1" @click="handleClick(scope.row,4)" type="info" size="mini">禁用</el-button>
                    <el-button v-if="scope.row.state == 2" @click="handleClick(scope.row,5)" type="success" size="mini">启用</el-button>
                    <el-button @click="handleClick(scope.row,3)" type="button" size="mini">编辑</el-button>
                    <el-button @click="handleClick(scope.row,2)" type="danger" :loading="false" size="mini">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination :background="true" @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" :page-sizes="[10,15,20,30,50,100]" :page-size="10" layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
    </el-main>
    <el-dialog :title="detail.title" :visible.sync="detail.dialogVisible" width="70%">
        <div>
            <el-form :model="user" ref="empForm">
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="登录名:" prop="username">
                            <el-input size="small" style="width: 150px" prefix-icon="el-icon-edit" v-model="user.username" clearable placeholder="请输入登录名"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="密码" prop="password">
                            <el-input type="password" size="small" style="width: 180px" v-model="user.password" placeholder="请输入密码" show-password></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="确认密码" prop="repassword">
                            <el-input type="password" size="small" style="width: 180px" v-model="user.repassword" placeholder="请再次输入密码" show-password></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="真实姓名:" prop="fullname">
                            <el-input size="small" style="width: 150px" prefix-icon="el-icon-edit" v-model="user.fullname" placeholder="请输入员工姓名"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="出生日期">
                            <el-date-picker size="small" v-model="user.birthday" type="date" placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="性别:" prop="gender">
                            <el-radio-group v-model="user.gender">
                                <el-radio label="男">男</el-radio>
                                <el-radio label="女">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="邮箱:" prop="email">
                            <el-input size="small" style="width: 150px" prefix-icon="el-icon-edit" v-model="user.email" placeholder="请输入邮箱"></el-input>
                            <el-select v-model="select" slot="prepend" placeholder="请选择">
                                <el-option label="餐厅名" value="1"></el-option>
                                <el-option label="订单号" value="2"></el-option>
                                <el-option label="用户电话" value="3"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="电话:" prop="phone">
                            <el-input size="small" style="width: 150px" prefix-icon="el-icon-edit" v-model="user.phone" placeholder="请输入电话"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
            <el-button @click="detail.dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="doAddUser">确 定</el-button>
        </span>
    </el-dialog>
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
                fullname: '',
                phone: '',
                email: '',
                state: '',
                beginDateScope: '',
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
                    label: '活动'
                },
                {
                    value: 2,
                    label: '禁用'
                }
            ],
            detail: {
                title: '',
                dialogVisible: false,
            },
            user: {
                id: '',
                username: '',
                password: '',
                repassword: '',
                fullname: '',
                gender: '',
                email: '',
                phone: '',
                birthday: ''
            },
            currentPage: 1,
            pageSize: 10,
            total: 0,
            loading: false,
            multipleSelection: [],
            tableData: [],
            select: ''
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
                    if (row.id == 1) {
                        this.$message({
                            type: 'warning',
                            message: '这是大哥，不能惹！'
                        });
                        return;
                    }
                    this.postRequest('/user/base/delete', {
                        'id': row.id
                    }).then(resp => {
                        var _this = this;
                        if (resp && 200 == resp.code) {
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

            } else if (4 == tag || 5 == tag) {
                /**禁用和启用 */
                //复制一个对象，但取消和源对象的关联性
                var this_row = Object.assign({}, row);
                var _state = 4 == tag ? 2 : 1;
                this_row.state = _state;
                this.postRequest('/user/base/update', this_row).then(resp => {
                    if (resp && 200 == resp.code) {
                        this.initList();
                    }
                })
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
            return 1 == cellValue ? "<span style='color:green'>正常</span>" : "<span style='color:red'>禁用</span>";
        },
        //开启新增窗口
        openWindows() {
            this.detail.title = '新增用户';
            this.detail.dialogVisible = true;
        },
        doAddUser() {
            this.postRequest('/user/base/add', this.user).then(resp => {
                if (resp && 200 == resp.code) {
                    this.initList();
                }
            })
        },
        /**列表查询 */
        initList() {
            this.loading = true;
            var search_data = Object.assign({}, this.searchData);
            search_data.page = this.currentPage;
            search_data.size = this.pageSize;
            search_data.dateScope = this.searchData.beginDateScope ? (this.searchData.beginDateScope[0] + ',' + this.searchData.beginDateScope[1]) : '';
            console.log(this.searchData.beginDateScope)
            this.postRequest('/user/base/', search_data).then(resp => {
                var _this = this;
                if (resp.code == 200) {
                    _this.loading = false;
                    _this.tableData = resp.data.rows;
                    _this.total = resp.data.total;
                    _this.currentPage = resp.data.pageNum;
                }

            })
        }

    },
}
</script>

<style scoped>
.el-main {
    padding: 5px 20px 5px 0;
}

.el-pagination {
    margin-top: 10px;
}

.el-header {
    /* height: 60px; */
    height: unset !important;
    min-height: 40px;
    padding: 0;
}

.el-header .form_input {
    width: 150px;
}

.el-header .el-button--mini {
    vertical-align: top;
}
</style>
