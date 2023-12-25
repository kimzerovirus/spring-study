class AutoTagInput {
    constructor(target, url) {
        this.target = target;
        this.el = document.createElement('input');
        this.url = url;
        this.flag = true;

        this.tagData = new Set();

        this.target.classList.add('form-group')
        this.el.classList.add('form-control')
        this.el.addEventListener('input', this.onInput);
        this.target.appendChild(this.el);
    }

    onInput() {
        if (!this.flag) {
            return;
        } else if (this.flag) {
            this.flag = false;
            setTimeout(() => {
                this.flag = true;
            }, 300)
        }

        this.removeDropdown();

        const value = this.el.value.toLowerCase();
        if(value === '') return;

        const data = this.getData()
        this.createDropdown(data)
    }

    createTagBox(){
        const tagBox = document.createElement('div');
        tagBox.classList.add('my-2');
    }

    createTags(){

    }

    addTag(){

    }

    removeTag(){

    }

    createDropdown(tags){
        const list = document.createElement('ul');
        list.classList.add('autocomplete-list', 'list-group');

        if(tags.length === 0) {
            this.createListItem();
            this.listItemBtn.innerText = 'create: ' + this.el.value;

            return;
        }

        tags.forEach(tag => {
            this.createListItem();

        });
    }

    createListItem(){
        const listItem = document.createElement('li');
        listItem.classList.add('list-group-item');

        const btn = document.createElement('button');
        btn.classList.add('btn btn-outline-primary');

        this.listItemBtn = btn;
    }

    removeDropdown() {
        const list = document.querySelector(".autocomplete-list");
        if (list) list.remove();
    }

    async getData() {
        const res = await fetch(this.url);
        return await res.json();
    }

    getTags(){
        return this.tagData;
    }
}