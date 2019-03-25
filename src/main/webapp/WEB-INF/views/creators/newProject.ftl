<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>

<@c.page "Project">
    <div class="profile-main-block container">
        <div class="float-left profile-main-inf-l profile-main-inf col-3 min-width">
            <#if user??>
                <#if user.getImg()??>
                    <div class="profile-photo"
                         style="background: url('${context.getContextPath()}/img/${user.getImg().getFullName()}') center no-repeat">
                    </div>
                <#else>
                    <div class="profile-photo"
                         style="background: url('https://www.cierpgaud.fr/wp-content/uploads/2018/07/avatar.jpg')  center no-repeat"></div>
                </#if>
            </#if>
            <div class="profile-name">
                <span>${user.firstName} ${user.lastName}</span>
            </div>
            <#if isCurrentUser>
                <form action="${context.getContextPath()}/profileSettings">
                    <input type="submit" value="Edit" class="profile-edit-submit"/>
                </form>
            </#if>
        </div>
        <div class="float-left profile-main-inf profile-main-inf-r profile-add-project">
            <div class="row">
                <nav class="navbar navbar-expand navbar-light pl-0">
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="#">Overview</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Projects</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Settings</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link add-link" href="#">Add project</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="profile-add-project-container">
                <div class="row mb-4">
                    <div class="text-left col-12 pl-0"><h1>Create a new project</h1>
                    </div>
                </div>
                <div class="row add-project-form">
                    <form class="add-project-form" action="${context.getContextPath()}/newProject" method="post">
                        <@spring.bind "form"/>
                        <label for="nameField">
                            <span>Project name</span>
                            <@spring.formInput "form.name" "id = 'nameField'"/>
                        </label><br>
                        <@spring.showErrors "form.name"/>
                        <label for="descriptionField">
                            <span>Description</span>
                            <@spring.formTextarea "form.description" "id = 'descriptionField'"/>
                        </label><br>
                        <@spring.showErrors "form.description"/>
                        <input type="submit" value="Create" class="add-project-submit"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@c.page>