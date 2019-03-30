<#macro projectNavbar>
    <nav class="navbar navbar-expand navbar-light">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${context.getContextPath()}/project/${project.id}">Overview</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${context.getContextPath()}/project/members/${project.id}">Members</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${context.getContextPath()}/project/tasks/${project.id}">Tasks</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${context.getContextPath()}/project/messages/${project.id}">Messages</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${context.getContextPath()}/project/files/${project.id}">Files</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${context.getContextPath()}/project/settings/${project.id}">Settings</a>
                </li>
            </ul>
        </div>
    </nav>
</#macro>