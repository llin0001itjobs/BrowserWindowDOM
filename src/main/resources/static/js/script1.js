/*
$(document).ready(function() {
	$('.item').click(function() {
		var sublist = $(this).next('.sublist');
		var toggle = $(this).find('.toggle');
		alert($(this));
		if (sublist.is(':visible')) {
			sublist.slideUp();
			toggle.text('+');
		} else {
			sublist.slideDown();
			toggle.text('-'); 
		}
	});
	$('#userAgentTitle').text(navigator.userAgent);

	buildList($('#my-list'));
});
*/
/*
  The key change here is the use of event delegation with the $(document).on('click', '.item', function() { ... }) handler. 
  This allows the click event to be captured for dynamically created .item elements within the #my-list element.
*/

$(document).ready(function() {
	$(document).on('click', '.item', function() {
		var sublist = $(this).next('.sublist');
		var toggle = $(this).find('.toggle');
		if (sublist.is(':visible')) {
			sublist.slideUp();
			toggle.text('+');
		} else {
			sublist.slideDown();
			toggle.text('-');
		}
	});

	$('#userAgentTitle').text(navigator.userAgent);

	buildList($('#my-list'));
});

$(document).ready(function() {
	$('.modal-anchor').on('click', function(e) {
		e.preventDefault();
		var str = window[e.target.dataset.value];
		str = JSON.stringify(str);		
		var text = $(this).text();
		var obj = window[text];
		if (typeof obj === 'function') {
			str = obj.toString();		
		}		
		$('#exampleModalLabelTitle').html(text);		
		$('#exampleModalLabel').html(str);
	});
});


function buildList(myList) {
	var arr = getWindowPropeties(window);
	var buckets = distributeItems(arr, 10);
	createListFromBuckets(buckets, myList);
}

function getWindowPropeties(o) {
	var arr = [];
	for (var item in o) {
		if (o.hasOwnProperty(item)) { // Make sure to check for own properties to avoid adding unwanted inherited properties			
			arr.push(item);
		}
	}
	arr.sort();
	return arr;
}

function distributeItems(items, bucketSize) {
	const buckets = [];
	const bucketsCount = (items.length / bucketSize) + 1;

	for (let i = 0; i < bucketsCount; i++) {
		buckets[i] = [];
	}

	var bucketIndex = 0;
	for (let i = 0; i < items.length; i++) {
		if (buckets[bucketIndex].length <= bucketSize) {
			buckets[bucketIndex].push(items[i]);
		} else {
			bucketIndex++;
			buckets[bucketIndex].push(items[i]);
		}
	}

	return buckets;
}

function createListFromBuckets(arr, elem) {
	for (var i = 0; i < arr.length; i++) {
		if (arr[i][0] === undefined) continue;
		var li = $('<li>');
		var div = $('<div>').addClass('item');
		var span_toggle = $('<span>').addClass('toggle').text('+');
		var span_title = $('<span>').addClass('spanTitle').text(arr[i][0] + ' - ' + arr[i][arr[i].length - 1]);
		elem.append(li);
		li.append(div);
		div.append(span_toggle);
		div.append(span_title);
		var ul = $('<ul>').addClass('sublist');
		for (var j = 0; j < arr[i].length; j++) {
			var sub_li = $('<li>');
			var value = arr[i][j];
			var anchor = $('<a>').addClass('modal-anchor').attr('href', '#').attr('data-value', value).text(value);
			sub_li.append(anchor);
			ul.append(sub_li);
		}
		li.append(ul);
	}
}

function printBuckets(buckets) {
	var str = '';
	for (let i = 0; i < buckets.length; i++) {
		for (let j = 0; j < buckets[i].length; j++) {
			str = str + buckets[i][j] + '<br/>';
		}
	}
	document.writeln(str);
}
