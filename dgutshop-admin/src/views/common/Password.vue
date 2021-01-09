<template>
  <div class="app-container" style="text-align:center;">
    <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;margin: auto;margin-top: 200px;">
      <el-form-item label="原密码" prop="oldPassword">
        <el-input v-model="dataForm.oldPassword" type="password"/>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input v-model="dataForm.newPassword" type="password" auto-complete="off"/>
      </el-form-item>
      <el-form-item label="确认密码" prop="newPassword2">
        <el-input v-model="dataForm.newPassword2" type="password" auto-complete="off"/>
      </el-form-item>
    </el-form>
    <div style="margin-left:100px;">
      <el-button @click="cancel">取消</el-button>
      <el-button type="primary" @click="change">确定</el-button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'ChangePassword',
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.dataForm.newPassword) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      dataForm: {
        oldPassword: '',
        newPassword: '',
        newPassword2: ''
      },
      rules: {
        oldPassword: [
          { required: true, message: '旧密码不能为空', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '新密码不能为空', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ],
        newPassword2: [
          { required: true, message: '确认密码不能为空', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    cancel() {
      this.dataForm = {
        oldPassword: '',
        newPassword: '',
        newPassword2: ''
      }
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    change() {
      this.$refs['dataForm'].validate((valid) => {
        if (!valid) {
          return
        }
          this.$confirm("此操作将修改个人密码, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true,
          })
            .then(async () => {
              const { data: res } = await this.$http.post(
                "/admin/profile/password",
                this.dataForm
              );
              console.log(res);
              if(res.errno===0){
              this.$message({
                type: "success",
                message: "修改成功!",
              });
              this.cancel()
              }

            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "发生错误",
              });
              this.cancel()
            });
      })
      
    }
  }
}
</script>
