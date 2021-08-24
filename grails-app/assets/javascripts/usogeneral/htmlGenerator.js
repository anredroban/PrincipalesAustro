/**
 * Created by cedancp on 1/5/17.
 */

function Field(id,name,type,validations,options,checked,value, parent){
    this.id = id
    this.name = name
    this.type = type
    this.checked = checked
    this.value = value
    this.validations = validations
    this.options = options
    this.parent = parent
}

function Validation(type,value,active){
    this.type = type
    this.value = value
    this.active = active
}

function OptionList(id, name,value,label) {
    this.id = id
    this.name = name
    this.value = value
    this.label = label
}

function Parent(id, name,value,label) {
    this.id = id
    this.name = name
    this.value = value
    this.label = label
}

function getTextField(field) {
    // alert (JSON.stringify(field))
    var div = document.createElement("div")
    var input = document.createElement("input")
    var label = document.createElement("label")

    if(field["name"]!=null){
        label.innerHTML= field["name"]
    }

    input = addAttributes(input,field)

    div.appendChild(label)
    div.appendChild(input)
    return div
}

function getSelectField(field){
    var div = document.createElement("div")
    var select = document.createElement("select")
    var label = document.createElement("label")

    if(field["name"]!=null){
        label.innerHTML= field["name"]
    }

    select = addAttributes(select,field)
    field["options"].forEach(function (option) {
        var optionElement = document.createElement("option")
        optionElement.setAttribute("id",option["id"])
        optionElement.setAttribute("value",option["value"])
        optionElement.setAttribute("name",option["name"])
        optionElement.innerHTML = option["name"]
        select.add(optionElement)
    })
    div.appendChild(label)
    div.appendChild(select)
    return div

}

function getTextArea(field){
    // alert (JSON.stringify(field))
    var div = document.createElement("div")
    var textArea = document.createElement("textarea")
    var label = document.createElement("label")

    if(field["name"]!=null){
        label.innerHTML= field["name"]
    }

    textArea = addAttributes(textArea,field)

    div.appendChild(label)
    div.appendChild(textArea)
    return div
}

function getCheckBox(field) {
    // alert (JSON.stringify(field))
    var div = document.createElement("div")
    var checkbox = document.createElement('input')
    checkbox.setAttribute("type", "checkbox")
    var label = document.createElement("label")

    if(field["name"]!=null){
        label.innerHTML= field["name"]
    }

    if(field["checked"]==true){
        checkbox.checked = true
    }

    checkbox = addAttributes(checkbox,field)

    div.appendChild(label)
    div.appendChild(checkbox)
    return div
}

function getRadioGroup(field){
    // alert (JSON.stringify(field))
    var div = document.createElement("div")
    var label = document.createElement("label")
    var divRadio = document.createElement("div")
    if(field["name"]!=null){
        label.innerHTML= field["name"]
    }
    div.appendChild(label)
    div.innerHTML = div.innerHTML + "<br>"
    field["options"].forEach(function (option) {
        divRadio.setAttribute("class","custom-control-input")
        var optionElement = document.createElement("input")
        optionElement.setAttribute("type", "radio")
        optionElement.setAttribute("id",option["id"])
        optionElement.setAttribute("value",option["value"])
        optionElement.setAttribute("name",field["name"])
        divRadio.appendChild(optionElement)
        divRadio.innerHTML = divRadio.innerHTML + option["name"] + "<br>"

    })
    div.appendChild(divRadio)
    return div
}

function addAttributes(element,fieldJSON){
    element.setAttribute("id", fieldJSON["id"])
    var numeric = false
    if(fieldJSON["value"]!=null){
        element.setAttribute("value", fieldJSON["value"])
    }

    if(fieldJSON["validations"]!=null) {
        fieldJSON["validations"].forEach(function (validation) {
            if (validation["type"] == "num") {
                if (validation["active"] == true) {
                    numeric = true
                }
            }
        })
        fieldJSON["validations"].forEach(function (validation) {
            if (validation["type"] == "req") {
                if (validation["active"] == true) {
                    element.setAttribute("required", true)
                }
            }
            if (validation["type"] == "num") {
                if (validation["active"] == true) {
                    element.setAttribute("type", "number")
                }
            }
            if (validation["type"] == "max") {
                if (validation["active"] == true) {
                    if (numeric) {
                        var maximum = 1
                        for (var i = 0; i < validation["value"]; i++) {
                            maximum = maximum * 10
                        }
                        if(validation["value"]==0) maximum = 0
                        element.setAttribute("max", maximum - 1)
                    } else {
                        element.setAttribute("maxlength", validation["value"])
                    }
                }
            }
            if (validation["type"] == "min") {
                if (validation["active"] == true) {
                    if (numeric) {
                        var minimum = 1
                        for (var i = 1; i < validation["value"]; i++) {
                            minimum = minimum * 10
                        }
                        if(validation["value"]==0) minimum = 0
                        element.setAttribute("min", minimum)
                    } else {
                        element.setAttribute("pattern", ".{" + validation["value"] + ",}")
                    }

                }
            }
        })
    }
    return element
}

function getField(field){
    if(field["type"]==1){
        return getTextField(field)
    }else if(field["type"]==2){
        return getSelectField(field)
    }else if(field["type"]==3){
        return getTextArea(field)
    }else if(field["type"]==4){
        return getCheckBox(field)
    }else if(field["type"]==5){
        return getRadioGroup(field)
    }
}
