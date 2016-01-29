routerApp.service('sharedProperty',function(){
	
	var rem='';
	
	return {
		
		getRem : function(){
			return rem;
		},
		setRem : function(val){
			rem = val;
		}
	};
});