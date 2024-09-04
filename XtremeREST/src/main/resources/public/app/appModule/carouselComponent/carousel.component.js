angular.module('appModule')
.component("carouselComponent", {
	templateUrl : "app/appModule/carouselComponent/carousel.component.html",
	controller : function(){
		
		  var vm = this;
		
		  vm.myInterval = 100;
		  vm.noWrapSlides = false;
		  vm.active = 0;
		  
		  var slides = vm.slides = [];
		  var currIndex = 0;
		  
		  vm.addSlide = function(url) {
//			    var newWidth = 600 + slides.length + 1;
			    slides.push({
			      image: url,
			      id: currIndex++
			    });
		  };
		  
		  function loadSlides(){
			  vm.addSlide('https://media-cdn.tripadvisor.com/media/photo-s/11/5f/9e/10/one-of-my-training-jumps.jpg');

			  vm.addSlide('http://sbcskier.com/wp-content/uploads/Publisher/5966/RB_1976_2011_-6006.jpg');
		  }
		  
		  loadSlides();

		  // Randomize logic below

		  function assignNewIndexesToSlides(indexes) {
		    for (var i = 0, l = slides.length; i < l; i++) {
		      slides[i].id = indexes.pop();
		    }
		  }

		  function generateIndexesArray() {
		    var indexes = [];
		    for (var i = 0; i < currIndex; ++i) {
		      indexes[i] = i;
		    }
		    return shuffle(indexes);
		  }

		  function shuffle(array) {
		    var tmp, current, top = array.length;

		    if (top) {
		      while (--top) {
		        current = Math.floor(Math.random() * (top + 1));
		        tmp = array[current];
		        array[current] = array[top];
		        array[top] = tmp;
		      }
		    }

		    return array;
		  }
	},
	controllerAs : 'vm'
});