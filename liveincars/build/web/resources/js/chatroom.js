var chatLetService = {
	_SELF : {},
	LOADMEASSAGESINTERVAL : messages_refreshInterval,
	LOADUSERSONLINEINTERVAL : users_refreshInterval,
	
	init : function() {
		_SELF = this;
	},

	checkEnter : function(event) {
		var key = event.keyCode || event.which;
		if (key == 13)
			$("#messages_button_send").click();
	},

	loadMessages : function() {
		var element = $("#messages_input");
		var previousScrollHeight = element[0].scrollHeight;
		newmes = document.createElement("span");
		$(newmes).load("readmessages", {}, function(response) {
			if (response.length > 0) {
				element.append(newmes);
				_SELF.scrollDown(previousScrollHeight);
			}
		});
	},

	sendingMessage : function(data) {
		output = $("#output_message");
		if (data.status == "success") {
			output.val("");
			_SELF.loadMessages();
		}
		output.focus();
	},

	scrollDown : function(previousScrollHeight) {
		element = $("#messages_input");
		newScrollHeight = element[0].scrollHeight;
		if (newScrollHeight != previousScrollHeight) {
			element
					.scrollTop(element[0].scrollHeight
							- element[0].clientHeight);
			_SELF.previousScrollHeight = newScrollHeight;
		}
	},
	previousScrollHeight : 0,

	loadUsersOnline : function() {
		element = $("#users_online");
		element.load("onlineusers");
	},

	setFocus : function() {
		$("#output_message").focus();
	},

	setEnterKeylistenerForMessageOutput : function() {
		$("output_message").context.addEventListener("keyup",
				_SELF.checkEnter);
	},

	setTimerForLoadMeassages : function() {
		setInterval(_SELF.loadMessages, _SELF.LOADMEASSAGESINTERVAL);
	},

	setTimerForLoadUsersOnline : function() {
		setInterval(_SELF.loadUsersOnline, _SELF.LOADUSERSONLINEINTERVAL);
	},

	startup : function() {
		// слушатель для клавиши Enter
		_SELF.setEnterKeylistenerForMessageOutput();
		// Таймеры для обновления содержания чата
		_SELF.loadMessages();
		_SELF.loadUsersOnline();
		_SELF.setTimerForLoadMeassages();
		_SELF.setTimerForLoadUsersOnline();
		// Фокус в поле ввода
		_SELF.setFocus();
	}
}
chatLetService.init();

window.onload = chatLetService.startup;
