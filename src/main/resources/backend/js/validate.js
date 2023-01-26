function isValidUsername(str) {
  return ['admin', 'editor'].indexOf(str.trim()) >= 0;
}

function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path);
}

function isCellPhone(val) {
  if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
    return false
  } else {
    return true
  }
}

function checkUserName(rule, value, callback) {
  if (value == "") {
    callback(new Error("please input account"))
  } else if (value.length > 20 || value.length < 3) {
    callback(new Error("length 3-20"))
  } else {
    callback()
  }
}

function checkShopId(rule, value, callback) {
  console.log(value)
  if ((value.length === 19) || value === '0' || value === 0) {
    console.log(111)
    callback()
  } else {
    callback(new Error("invalid input"))
  }
}
