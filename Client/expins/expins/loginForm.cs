using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using expins.userService;

namespace expins
{
    public partial class loginForm : Form
    {
        public loginForm()
        {
            InitializeComponent();
        }

        private void userControl11_Click(object sender, EventArgs e)
        {
            //MessageBox.Show("1");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            //userNameBox.Text = "admin";
            //passwordBox.Text = "admin";
            //MessageBox.Show( uv.userRoles.Length.ToString());
            //MessageBox.Show(uv.userRoles[0].roleId.ToString());


            if (userNameBox.Text.Length == 0 || passwordBox.Text.Length == 0)
            {
                MessageBox.Show("用户名或密码不能为空");
            }
            else
            {
                UserServerPortTypeClient client = new UserServerPortTypeClient();
                UsersVo uv = client.getUsersByLoginNamePassWord(userNameBox.Text.ToString(), passwordBox.Text.ToString());
                if (uv.userRoles.Length == 1)
                {
                    switch (uv.userRoles[0].roleName)
                    {
                        case "admin": MessageBox.Show("welcome:" + uv.userRoles[0].roleName);
                                      adminForm f2m = new adminForm();
                                      f2m.Show();
                                      this.Visible = false;
                                      break;
                        //default: MessageBox.Show("Sorry,you should login with a registed account");
                    }
                }
                else
                {
                    //do nothing
                }

                //adminForm f2m = new adminForm();
                //f2m.Show();
                //this.Visible = false;
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

    }
}
