﻿//------------------------------------------------------------------------------
// <auto-generated>
//     此代码由工具生成。
//     运行时版本:2.0.50727.3615
//
//     对此文件的更改可能会导致不正确的行为，并且如果
//     重新生成代码，这些更改将会丢失。
// </auto-generated>
//------------------------------------------------------------------------------

namespace expins.userService {
    using System.Runtime.Serialization;
    using System;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "3.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="UserDetailVo", Namespace="http://vo.user.rfid.com")]
    [System.SerializableAttribute()]
    public partial class UserDetailVo : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private System.DateTime birthdayField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string connectionField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private System.Nullable<long> idField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private System.DateTime registerTimeField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string userAddressField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string userNameField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private expins.userService.UsersVo usersVoField;
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.DateTime birthday {
            get {
                return this.birthdayField;
            }
            set {
                if ((this.birthdayField.Equals(value) != true)) {
                    this.birthdayField = value;
                    this.RaisePropertyChanged("birthday");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string connection {
            get {
                return this.connectionField;
            }
            set {
                if ((object.ReferenceEquals(this.connectionField, value) != true)) {
                    this.connectionField = value;
                    this.RaisePropertyChanged("connection");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.Nullable<long> id {
            get {
                return this.idField;
            }
            set {
                if ((this.idField.Equals(value) != true)) {
                    this.idField = value;
                    this.RaisePropertyChanged("id");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.DateTime registerTime {
            get {
                return this.registerTimeField;
            }
            set {
                if ((this.registerTimeField.Equals(value) != true)) {
                    this.registerTimeField = value;
                    this.RaisePropertyChanged("registerTime");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string userAddress {
            get {
                return this.userAddressField;
            }
            set {
                if ((object.ReferenceEquals(this.userAddressField, value) != true)) {
                    this.userAddressField = value;
                    this.RaisePropertyChanged("userAddress");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string userName {
            get {
                return this.userNameField;
            }
            set {
                if ((object.ReferenceEquals(this.userNameField, value) != true)) {
                    this.userNameField = value;
                    this.RaisePropertyChanged("userName");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public expins.userService.UsersVo usersVo {
            get {
                return this.usersVoField;
            }
            set {
                if ((object.ReferenceEquals(this.usersVoField, value) != true)) {
                    this.usersVoField = value;
                    this.RaisePropertyChanged("usersVo");
                }
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "3.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="UsersVo", Namespace="http://vo.user.rfid.com")]
    [System.SerializableAttribute()]
    public partial class UsersVo : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private System.Nullable<long> idField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string loginNameField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string loginPasswordField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private expins.userService.RolesVo[] userRolesField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private System.Nullable<long> useridField;
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.Nullable<long> id {
            get {
                return this.idField;
            }
            set {
                if ((this.idField.Equals(value) != true)) {
                    this.idField = value;
                    this.RaisePropertyChanged("id");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string loginName {
            get {
                return this.loginNameField;
            }
            set {
                if ((object.ReferenceEquals(this.loginNameField, value) != true)) {
                    this.loginNameField = value;
                    this.RaisePropertyChanged("loginName");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string loginPassword {
            get {
                return this.loginPasswordField;
            }
            set {
                if ((object.ReferenceEquals(this.loginPasswordField, value) != true)) {
                    this.loginPasswordField = value;
                    this.RaisePropertyChanged("loginPassword");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public expins.userService.RolesVo[] userRoles {
            get {
                return this.userRolesField;
            }
            set {
                if ((object.ReferenceEquals(this.userRolesField, value) != true)) {
                    this.userRolesField = value;
                    this.RaisePropertyChanged("userRoles");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.Nullable<long> userid {
            get {
                return this.useridField;
            }
            set {
                if ((this.useridField.Equals(value) != true)) {
                    this.useridField = value;
                    this.RaisePropertyChanged("userid");
                }
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "3.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="RolesVo", Namespace="http://vo.user.rfid.com")]
    [System.SerializableAttribute()]
    public partial class RolesVo : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private System.Nullable<long> idField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private System.Nullable<int> isEnableField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private System.Nullable<long> roleIdField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string roleNameField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string roleNoteField;
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.Nullable<long> id {
            get {
                return this.idField;
            }
            set {
                if ((this.idField.Equals(value) != true)) {
                    this.idField = value;
                    this.RaisePropertyChanged("id");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.Nullable<int> isEnable {
            get {
                return this.isEnableField;
            }
            set {
                if ((this.isEnableField.Equals(value) != true)) {
                    this.isEnableField = value;
                    this.RaisePropertyChanged("isEnable");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.Nullable<long> roleId {
            get {
                return this.roleIdField;
            }
            set {
                if ((this.roleIdField.Equals(value) != true)) {
                    this.roleIdField = value;
                    this.RaisePropertyChanged("roleId");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string roleName {
            get {
                return this.roleNameField;
            }
            set {
                if ((object.ReferenceEquals(this.roleNameField, value) != true)) {
                    this.roleNameField = value;
                    this.RaisePropertyChanged("roleName");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string roleNote {
            get {
                return this.roleNoteField;
            }
            set {
                if ((object.ReferenceEquals(this.roleNoteField, value) != true)) {
                    this.roleNoteField = value;
                    this.RaisePropertyChanged("roleNote");
                }
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://server.user.rfid.com", ConfigurationName="userService.UserServerPortType")]
    public interface UserServerPortType {
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="out")]
        bool regUsers(expins.userService.UserDetailVo in0);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="out")]
        bool loginAble(string in0, string in1);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="out")]
        expins.userService.UsersVo getUsersByLoginNamePassWord(string in0, string in1);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="out")]
        bool regUsers1(expins.userService.UsersVo in0);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="out")]
        expins.userService.UsersVo getUsersByUserId(System.Nullable<long> in0);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="out")]
        bool hasUserByLoginName(string in0);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="out")]
        expins.userService.UserDetailVo getUserDetailByUserId(System.Nullable<long> in0);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="out")]
        expins.userService.UsersVo[] getAllUsers();
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="out")]
        expins.userService.UserDetailVo getUserDetailByLoginNamePassWord(string in0, string in1);
        
        [System.ServiceModel.OperationContractAttribute(Action="", ReplyAction="*")]
        [return: System.ServiceModel.MessageParameterAttribute(Name="out")]
        expins.userService.UserDetailVo[] getAllUserDetail();
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
    public interface UserServerPortTypeChannel : expins.userService.UserServerPortType, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "3.0.0.0")]
    public partial class UserServerPortTypeClient : System.ServiceModel.ClientBase<expins.userService.UserServerPortType>, expins.userService.UserServerPortType {
        
        public UserServerPortTypeClient() {
        }
        
        public UserServerPortTypeClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public UserServerPortTypeClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public UserServerPortTypeClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public UserServerPortTypeClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        public bool regUsers(expins.userService.UserDetailVo in0) {
            return base.Channel.regUsers(in0);
        }
        
        public bool loginAble(string in0, string in1) {
            return base.Channel.loginAble(in0, in1);
        }
        
        public expins.userService.UsersVo getUsersByLoginNamePassWord(string in0, string in1) {
            return base.Channel.getUsersByLoginNamePassWord(in0, in1);
        }
        
        public bool regUsers1(expins.userService.UsersVo in0) {
            return base.Channel.regUsers1(in0);
        }
        
        public expins.userService.UsersVo getUsersByUserId(System.Nullable<long> in0) {
            return base.Channel.getUsersByUserId(in0);
        }
        
        public bool hasUserByLoginName(string in0) {
            return base.Channel.hasUserByLoginName(in0);
        }
        
        public expins.userService.UserDetailVo getUserDetailByUserId(System.Nullable<long> in0) {
            return base.Channel.getUserDetailByUserId(in0);
        }
        
        public expins.userService.UsersVo[] getAllUsers() {
            return base.Channel.getAllUsers();
        }
        
        public expins.userService.UserDetailVo getUserDetailByLoginNamePassWord(string in0, string in1) {
            return base.Channel.getUserDetailByLoginNamePassWord(in0, in1);
        }
        
        public expins.userService.UserDetailVo[] getAllUserDetail() {
            return base.Channel.getAllUserDetail();
        }
    }
}
