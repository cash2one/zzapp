/**
 * @author Administrator
 */
[
	 {text:"部门管理",leaf:true},
	 {text:"公司管理",leaf:true},
	 {
		 text:"权限管理",
		 children:[
					{text:"权限管理",leaf:true},
					{text:"权限类别",leaf:true}
				  ] 
	 },
	 {
		 text:"权限管理",
		 expanded:true,//伸展
		 children:[
					{text:"权限管理",leaf:true},
					{
						text:"权限类别",
						children:[
							{text:"权限管理",leaf:true}	
						]
					}
				  ] 
	 }
]