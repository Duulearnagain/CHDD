app.controller("product-ctrl",function($scope,$http){
	$scope.items=[];
	$scope.cates=[];
	$scope.form={};
	
	$scope.initialize=function(){
		//load product
		$http.get("/rest/products").then(resp =>{
			$scope.items=resp.data;//lay sp xong thi cho vao iteam ben tren de hien thi len table
			$scope.items.forEach(item =>{//chuyen doi ngay thang trong dl ->ngay thang trong js
				item.createDate= new Date(item.createDate)
			})
		});
		//load category
		$http.get("/rest/categories").then(resp =>{
			$scope.cates=resp.data;
			$scope.items.forEach(item =>{
				item.createDate= new Date(item.createDate)
			})
		});
	}
	// Khởi đầu
	$scope.initialize();


	//Xóa form
	$scope.reset=function(){
		$scope.form={
			createDate: new Date(),//ngay mac dinh se laf ngay hien tai
			image:'cloud-upload.jpg',//anh mac dinh
			avalibale:true,
		};
	}
	//hiển thị lên form
	$scope.edit=function(item){
		$scope.form=angular.copy(item);
		$(".nav-tabs a:eq(0)").tab("show")//khi click button se hien thi tab
	}
	//Thêm sản phẩm mới
	$scope.create=function(){
		var item=angular.copy($scope.form);//tao ra 1 item , post len dia chi
		$http.post('/rest/products',item).then(resp =>{
			resp.data.createDate= new Date(resp.data.createDate)//resp se tra ve ngay ta phai chuyen sang js
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới sản phẩm thành công!");
		}).catch(error =>{
			alert("Lỗi thêm mới sản phẩm!");
			console.log("Error",error);
		});
		
	}
	//cập nhật sản phẩm
	$scope.update=function(){
		var item=angular.copy($scope.form);//lay dl trong form
		$http.put(`/rest/products/${item.id}`,item).then(resp =>{//day len form voi method put
			var index= $scope.items.findIndex(p => p.id == item.id);// tim trong  product sp co id trung voi id mk chon 
			$scope.items[index]=item;//thi thay doi sp do ben trong list
			alert("Cập nhật sản phẩm thành công!");
		}).catch(error =>{
			alert("Lỗi cập nhật sản phẩm!");
			console.log("Error",error);
		});
		
	}
	//Xóa sản phẩm
	$scope.delete=function(item){
		$http.delete(`/rest/products/${item.id}`,item).then(resp =>{
			var index= $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index,1);//xoa 1 pt tai index
			$scope.reset();
			alert("Xóa sản phẩm thành công!");
		}).catch(error =>{
			alert("Lỗi xóa sản phẩm!");
			console.log("Error",error);
		});
		
	}
	//Upload hình
	$scope.imageChanged=function(files){
		var data= new FormData();//tao doi tuong formData
		data.append('file',files[0]);//lay file ma duoc chon bo vao data 
		$http.post('/rest/upload/images',data,{//post len server voi URL
			transformRequest: angular.identity,
			headers:{'Content-Type':undefined}
		}).then(resp =>{
			$scope.form.image=resp.data.name;//up thanh cong thi se tra ve repons va ta se lay name thay vao form de no hien thi len anh
		}).catch(error =>{
			alert("Lỗi uplaod hình ảnh");
			console.log("Error",error);
		} )
	}
	
	$scope.pager={
		page:0,
		size:10,
		get items(){//loc sp theo trang
			var start= this.page*this.size;
			return $scope.items.slice(start,start + this.size);
		},
		get count(){
			return Math.ceil(1.0 * $scope.items.length/this.size)
		},
		first(){
			this.page=0;
		},
		prev(){
			this.page--;
			if(this.page <0){
				this.last();
			}
		},
		next(){
			this.page++;
			if(this.page >=this.count){
				this.first();
			}
		},
		last(){
			this.page=this.count-1;
		},
	}
	
});